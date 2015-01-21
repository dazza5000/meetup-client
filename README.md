# meetup-client
A new Meetup API client using Retrofit from Square [ http://square.github.io/retrofit/ ]

To build:

    ./gradlew build

To install locally:

    ./gradlew install
    
To use in your gradle project, edit build.gradle to contain:

    repositories {
        mavenCentral()
        mavenLocal()
    }

    dependencies {
        compile 'com.bitgrind.meetup:meetup-client:1.0-SNAPSHOT'
    }
