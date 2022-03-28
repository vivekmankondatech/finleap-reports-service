# finleap-reports-service
Implementation of REST APIs for creating, editing and deleting Incident Reports related to an application running in production.

This is a REST-based Backend application that has the following Features:
- Create a new incident report
- List existing incident reports
- Edit an existing incident report
- Delete an existing incident report
- Create User
- Edit User
- Delete User

### Note:

- While running the app if you find your class changes are not reflecting. Then run an `./gradlew --refresh-dependencies clean build` command on the root project and finally run the spring boot application. This is basically to refresh all the library dependency if they are out of sync.

- Please note that shell commands in the root folder in `start-finleap-reports-service.sh` are tested on macOS Terminal. It should work just as fine in Ubuntu or Linux-based OS. For windows OS, it is advisable to use `PowerShell` and not the standard `cmd` to run the application.

### Assumptions


- Every user can read all the incident reports
- The Assignee and the creator of an incident report can update its details
- Only the Assignee can update the incident report status
- The Assignee is assigned automatically to an incident report from the list of unassigned users. This is because there was no requirement for listing available users to be assigned and selecting it manually.

### System Requirements

- Operating System (macOS, Linux-based OS, Windows)
- `Docker` must be installed in the system. To install the application, go to [Docker Website here](https://docs.docker.com/engine/install/) and follow the instructions there.

### Running the Application

These instructions are for running the application in a local environment. It is a simulation of production environment. 
For the production release, we can configure properties file and direct the docker to use `prod` as the active spring profile.

To START the Application:

- Open Terminal.
- Go to root project folder: `cd ../finleap-reports-service/`
- Start the application by using: `sh start-finleap-reports-service.sh`

If you see this message: `"Started FinleapReportsServiceApplication..."` in the terminal logs, your application has successfully started.

To execute the APIs, please use the swagger link provided in the next section.

If you wish to STOP the application, please use the following commands:

- Stop the terminal logs: Press `control + z`.
- Ensure you are in the root project folder.
- Stop the application by using: `sh stop-finleap-reports-service.sh`


### REST API Documentation

Click on the link below to access the Swagger REST Documentation. This can be used for executing all the APIs directly. Please refer to the `Models` section of the page to understand the Request and the Response Structures.

[Swagger Documentation](http://localhost:8090/api/swagger-ui/index.html)

If the above link doesn't work, please copy the link below in your browser.
http://localhost:8090/api/swagger-ui/index.html