# Environments

    The project is written in java code. The operating environment is as follows:
    *javaSE 8.0*

# How to run?
    java -jar COMP2811-PMA.jar
# How to run unit test?
    cd src/main/java
    javac -cp junit5.jar controller/*.java model/*.java
    java -jar junit5.jar -cp . --scan-classpath -n controller.BarcodeEnterControllerTest
# Design goals

    Prototype desktop program for designing drug information library 
    and weekly drug dose calculation for individual users
