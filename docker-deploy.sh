#!/bin/bash

# Make sure only root can run our script
if [[ $EUID -ne 0 ]]; then
  echo "This script must be run as root" 1>&2
  exit 1
fi

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

docker network create --driver bridge $NETWORK_NAME
docker run -d --network $NETWORK_NAME --name $MYSQLDB_CONTAINER_NAME -e MYSQL_ROOT_PASSWORD=$DB_PASSWORD -e MYSQL_DATABASE=$DB_NAME mysql:8
docker run -d --network $NETWORK_NAME --name $STOCK_MANAGER_CONTAINER_NAME -p $STOCK_MANAGER_PORT:$STOCK_MANAGER_PORT lucasvilela/stock-manager

#waiting for docker to start...
sec=30
while [ $sec -ge 0 ]; do
  echo -ne "  Waiting... ${sec}s\033[0K\r"
  (( sec=sec-1 ))
  sleep 1
done

docker run -d --network $NETWORK_NAME --name $QUOTATION_MANGER_CONTAINER_NAME -e SERVER_PORT=$QUOTATION_MANGER_PORT \
-e DATASOURCE_URL=$DB_URL \
-e DATASOURCE_USERNAME=$DB_USER \
-e DATASOURCE_PASSWORD=$DB_PASSWORD \
-e STOCK_MANAGER_STOCK_URL=$STOCK_URL \
-e STOCK_MANAGER_NOTIFICATION_URL=$NOTIFICATION_URL \
-e STOCK_MANAGER_NOTIFICATION_HOST=$NOTIFICATION_HOST \
-e STOCK_MANAGER_NOTIFICATION_PORT=$NOTIFICATION_PORT \
gabrieljuliao/quotation-manager

