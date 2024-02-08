How to use gradle-profiler

- Clone the gradle-profiler repository in some directory `git clone https://github.com/gradle/gradle-profiler.git`
- Open terminal (command prompt) from the gradle-profiler directory
- Run `gradlew installDist` to install gradle-profiler
  - Create your performance scenario file, for example: `performance.scenarios` 
    ```scenarios
        cold {
          tasks = ["clean", "assembleDebug"]
          iterations = 5
          warm-ups = 1
        }
    
        warm {
          tasks = ["clean", "assembleDebug"]
          iterations = 5
          warm-ups = 1
        }
          
        hot {
          tasks = ["clean", "assembleDebug"]
          iterations = 5
          warm-ups = 1
        }
    ```
- Make sure you already set your `GRADLE_USER_HOME` environment variable in your system
- Now you can run your gradle-profiler `gradle-profiler --benchmark --scenario-file performance.scenarios --gradle-user-home %GRADLE_USER_HOME%`