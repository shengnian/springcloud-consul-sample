# Spring boot with docker compose.

## Build project

`$git clone https://github.com/shengnian/springcloud-consul-sample.git`

`cd springcloud-consul-sample `

```
gradle projects

gradle :mics-blog:clean :mics-blog:bootRepackage :mics-blog:buildDocker

gradle :mics-client:clean :mics-client:bootRepackage :mics-client:buildDocker

gradle :mics-person:clean :mics-person:bootRepackage :mics-person:buildDocker

```
or 
```
gradle clean bootRepackage buildDocker

```

`docker-compose up -d`

and go to below link to test:
```
http://localhost:8500

http://localhost:8500/v1/catalog/service/shengnianos-consul-person

http://localhost:8082/hello?name=c

http://localhost:8083/
```

```
http://localhost:9401/discover

http://localhost:9401/services
```

`docker-compose stop`
or 
`docker-compose down` to remove all container.

if the container was run, use 
`docker-compose start` to restart the stoped container