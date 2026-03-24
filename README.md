#docker cmds to run redis instance and to open redis cli
docker run -d --name redis -p 6379:6379 redis:8.6
docker exec -it redis redis-cli


#swagger url
http://localhost:8080/swagger-ui/index.html
