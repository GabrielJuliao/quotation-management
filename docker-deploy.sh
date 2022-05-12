#!/bin/bash
# continers config
NETWORK_NAME="quotation-network"
#MySQL
MYSQLDB_CONTAINER_NAME="mysqldb"
#stock-manager
STOCK_MANAGER_CONTAINER_NAME="stock-manager"
STOCK_MANAGER_PORT=8080
#quotation-manager
QUOTATION_MANGER_CONTAINER_NAME="quotation-manager"
QUOTATION_MANGER_PORT=8081
STOCK_URL="http://${STOCK_MANAGER_CONTAINER_NAME}:${STOCK_MANAGER_PORT}/stock"
NOTIFICATION_URL="http://${STOCK_MANAGER_CONTAINER_NAME}:${STOCK_MANAGER_PORT}/notification"
NOTIFICATION_HOST=$QUOTATION_MANGER_CONTAINER_NAME
NOTIFICATION_PORT=$QUOTATION_MANGER_PORT
#MySQL
DB_NAME="bootdb"
DB_URL="jdbc:mysql://${MYSQLDB_CONTAINER_NAME}:3306/${DB_NAME}"
DB_USER="root"
DB_PASSWORD="root"

teardown() {
  echo WARNING! THIS WILL DESTROY ALL OF YOUR CONTAINERS AND IMAGES.
  read -p "Are you sure you want to continue? [y/N]" -n 1 -r
  echo # (optional) move to a new line
  if [[ $REPLY =~ ^[Yy]$ ]]; then
    docker stop $(docker ps -qa)
    docker container prune
    docker network rm $NETWORK_NAME
    docker network prune
    docker image rm $(docker images -qa)
    docker image prune
  fi
}

clear() {
  docker stop $QUOTATION_MANGER_CONTAINER_NAME $STOCK_MANAGER_CONTAINER_NAME $MYSQLDB_CONTAINER_NAME
  docker container rm $QUOTATION_MANGER_CONTAINER_NAME $STOCK_MANAGER_CONTAINER_NAME $MYSQLDB_CONTAINER_NAME
  docker network rm $NETWORK_NAME
}

deploy() {
  clear
  docker network create --driver bridge $NETWORK_NAME
  docker run -d --network $NETWORK_NAME --name $MYSQLDB_CONTAINER_NAME -p 3306:3306 -p 33060:33060 -e MYSQL_ROOT_PASSWORD=$DB_PASSWORD -e MYSQL_DATABASE=$DB_NAME mysql:8
  docker run -d --network $NETWORK_NAME --name $STOCK_MANAGER_CONTAINER_NAME -p $STOCK_MANAGER_PORT:$STOCK_MANAGER_PORT lucasvilela/stock-manager

  #waiting for docker to start...
  sec=30
  while [ $sec -ge 0 ]; do
    echo -ne "  Waiting... ${sec}s\033[0K\r"
    ((sec = sec - 1))
    sleep 1
  done

  docker run -d --network $NETWORK_NAME --name $QUOTATION_MANGER_CONTAINER_NAME -p $QUOTATION_MANGER_PORT:$QUOTATION_MANGER_PORT \
    -e DATASOURCE_URL=$DB_URL \
    -e DATASOURCE_USERNAME=$DB_USER \
    -e DATASOURCE_PASSWORD=$DB_PASSWORD \
    -e STOCK_MANAGER_STOCK_URL=$STOCK_URL \
    -e STOCK_MANAGER_NOTIFICATION_URL=$NOTIFICATION_URL \
    -e STOCK_MANAGER_NOTIFICATION_HOST=$NOTIFICATION_HOST \
    -e STOCK_MANAGER_NOTIFICATION_PORT=$NOTIFICATION_PORT \
    gabrieljuliao/quotation-manager
}

case "$1" in
-t | --teardown)
  teardown
  ;;
-c | --clear)
  clear
  ;;
*)
  deploy
  ;;
esac
