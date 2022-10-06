@REM don't have any options to test it. Hope it works

set SPRING_DATASOURCE_PASSWORD="city"
set SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5432/city"
set SPRING_DATASOURCE_USERNAME="city"

call .\mvnw clean verify