package app.databases;


import annotations.InitializerClass;
import annotations.InitializerMethod;
import annotations.RetryOperation;

import java.io.IOException;

@InitializerClass
public class DatabaseConnection {

    private int failCounter = 5;

    @RetryOperation(
            numberOfRetries = 10,
            retryExceptions = IOException.class,
            durationBetweenRetriesMs = 1000,
            failureMessage = "Connection to database 1 failed after retries"
    )
    @InitializerMethod
    public void connectToDatabase1() throws IOException {
        System.out.println("Connection to database 1");
        if (failCounter > 0) {
            failCounter--;
            throw new IOException("Connection failed");
        }
    }
    @InitializerMethod
    public void connectToDatabase2() {
        System.out.println("Connection to database 2");
    }
}
