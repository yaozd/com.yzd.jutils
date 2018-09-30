title build env is dev
call echo mvn package begin
call mvn clean
call mvn compile -Pdev
call mvn install -DskipTests
call echo mvn package end
call pause