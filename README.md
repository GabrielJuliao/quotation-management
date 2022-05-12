# quotation-management

## Swagger Endpoints
<ul>
<li>SwaggerUI: <code>/api/swagger-ui/index.html</code></li>
<li>API Docs: <code>/api/v3/api-docs</code></li>
</ul>

## Docker Deployment

### Automatically
<p>For deploying this application on Docker you can use the convinience script <code>docker-deploy.sh</code></p>

#### To:
<ul>
<li>auto-deploy: <code>./docker-deploy.sh</code></li>
<li>stop and remove the containers: <code>./docker-deploy.sh -c</code> or <code>./docker-deploy.sh --clear</code></li>
<p><b>WARNING!</b> Use the option below carefully, it will <b>DESTROY ALL</b> of your images and containers.</p>
<li>destroy all images and containers: <code>./docker-deploy.sh -t</code> or <code>./docker-deploy.sh --teardown</code></li>
</ul>

### Manually

#### To:
<ul>
<li>pull the image<code>docker pull gabrieljuliao/quotation-manager</code></li>
</ul>

The container accepts the following env variables:
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
