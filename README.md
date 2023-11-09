# Selenium-Java-AddressbookWebApp

To run the tests, you will need a local web server on which the Addressbook web application will be deployed.

Below are instructions on how to do this using Docker Personal (https://www.docker.com/products/personal)

After you run Docker, perform the following commands:

    docker pull barancev/web-apps-for-courses
    docker run -d --name webapps -p 80:80 --restart=always barancev/web-apps-for-courses


After that, you can access the application here: http://localhost/addressbook/


If port 80 is occupied on your computer (by another local web server), you can use a different port for the container, for example, 8080:

    docker run -d --name webapps -p 8080:80 --restart=always barancev/web-apps-for-courses
In this case, the port will also need to be specified in the address of the application: http://localhost:8080/addressbook/

You can stop the container with the command:
    
    docker stop webapps

You can start the container with the command:
    
    docker start webapps

When restarted, the application state is preserved. To completely reinitialize the application (return to its original state), you need to stop and delete the container:

    docker stop webapps
    docker rm webapps
and then create and run a new clean copy of the container with the command
    
    docker run -d --name webapps -p 80:80 --restart=always barancev/web-apps-for-courses
