# TourGuide
Project TourGuide - Openclassrooms P8

# Lien GitLab
https://gitlab.com/crolinux/TourGuide_GL

-------------------
File gitlab-ci.yml:
-------------------
stages:
  - build
  - test
  - package


before_script:
  - echo "Start"
  - echo "Install the jars"
  - ./mvnw install:install-file -Dfile=$CI_PROJECT_DIR/libs/gpsUtil.jar -DgroupId=gpsUtil -DartifactId=gpsUtil -Dversion=1.0.0 -Dpackaging=jar
  - ./mvnw install:install-file -Dfile=$CI_PROJECT_DIR/libs/RewardCentral.jar -DgroupId=rewardCentral -DartifactId=rewardCentral -Dversion=1.0.0 -Dpackaging=jar
  - ./mvnw install:install-file -Dfile=$CI_PROJECT_DIR/libs/TripPricer.jar -DgroupId=tripPricer -DartifactId=tripPricer -Dversion=1.0.0 -Dpackaging=jar
  - echo "The jars are installed"

build_job:
  stage: build
  image: eclipse-temurin:17-jdk-alpine
  script:
    - echo "Build job started"
    - ./mvnw compile
    - echo "Build job finished"

test_job:
  stage: test
  image: eclipse-temurin:17-jdk-alpine
  script:
    - echo "Test job started"
    - ./mvnw test
    - echo "Test job finished"

package_job:
  stage: package
  image: eclipse-temurin:17-jdk-alpine
  script:
    - echo "Package job started"
    - ./mvnw package
    - echo "Package job finished"
    - echo "Last file created:"
    - ls -lt target/ | head


