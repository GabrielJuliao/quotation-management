# quotation-management
Inatel Technical Evaluation 1
# Maven
<b>NOTE:</b> this applciation contains mutiple spring profiles, make sure you are using the right one when building it, the default is <code>application.properties</code>
#### To:
<ul>
<li>compile <code>mvnw compile</code></li>
<li>test <code>mvnw test</code></li>
<li>package <code>mvnw clean package -DskipTests</code> flag "-DskipTests" is necessary because the application rellies on an external MySQL database, only package without it if you have the required database up and running.</li>

</ul>

# Swagger Endpoints
<ul>
<li>Swagger UI: <code>/api/swagger-ui/index.html</code></li>
<li>API Docs: <code>/api/v3/api-docs</code></li>
</ul>

# Docker Deployment

## Automatically

### With Docker compose:

#### To:
<ul>
<li>run attached<code>docker compose up</code></li>
<li>run dettached<code>docker compose up -d</code></li>
<li>stop<code>docker compose stop</code></li>
</ul>

### Without Docker compose:
<p>For deploying this application on Docker without Docker compose you can use the convinience script <code>docker-deploy.sh</code></p>

#### To:
<ul>
<li>auto-deploy: <code>./docker-deploy.sh</code></li>
<li>stop and remove the containers: <code>./docker-deploy.sh -c</code> or <code>./docker-deploy.sh --clear</code></li>
<p><b>WARNING!</b> Use the option below carefully, it will <b>DESTROY ALL</b> of your images and containers.</p>
<li>destroy all images and containers: <code>./docker-deploy.sh -t</code> or <code>./docker-deploy.sh --teardown</code></li>
</ul>

## Manually

#### INFO:
<ul>
<li>docker hub image <code>gabrieljuliao/quotation-manager</code></li>
</ul>

#### ACCEPTED ENVIROMENT VARIABLES:
<ul>
<li><b>SERVER_PORT</b> default value: <code>8081</code></li>
<li><b>DATASOURCE_URL</b> default value: <code>jdbc:mysql://mysqldb:3306/bootdb</code></li>
<li><b>DATASOURCE_USERNAME</b> default value: <code>root</code></li>
<li><b>DATASOURCE_PASSWORD</b> default value: <code>root</code></li>
<li><b>STOCK_MANAGER_STOCK_URL</b> default value: <code>http://localhost:8080/stock</code></li>
<li><b>STOCK_MANAGER_NOTIFICATION_URL</b> default value: <code>http://localhost:8080/notification</code></li>
<li><b>STOCK_MANAGER_NOTIFICATION_HOST</b> default value: <code>localhost</code></li>
<li><b>STOCK_MANAGER_NOTIFICATION_PORT</b> default value: <code>8081</code></li>
</ul>
