# Local Deployment

## 1. Start a container

Starting a container is possible in **two ways**:
1. via command line: navigate into the `localdeployment` folder and enter *docker-compose up*
2. via IntelliJ IDEA: run the already existing configuration named *create container*

## 2. Database migration

In the next step we should complete the database migration. There are also **two** options:
1. in `db-migration` maven project via command line: `compile flyway:clean flyway:migrate -Dflyway.cleanDisabled=false`
2. via IntelliJ IDEA: run the already existing configuration named *migrate database*

## 3. Start application

Start the *SpringIntroductionApplication* run configuration.