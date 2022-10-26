


## Script engine

### Install recommended sdkman for managing JDKs

https://sdkman.io/install
    
    curl -s "https://get.sdkman.io" | bash
    source "$HOME/.sdkman/bin/sdkman-init.sh"


### Install GraalVM for Java 17
    sdk install java graalvm-17

### Install JavaScript engine
    gu install js

### Dependency
    implementation 'org.graalvm.js:js-scriptengine:22.0.0'
    implementation 'org.graalvm.js:js:22.0.0'
    implementation 'org.graalvm.compiler:compiler:22.0.0'
    implementation 'org.graalvm.sdk:graal-sdk:22.0.0'
    implementation 'org.graalvm.truffle:truffle-api:22.0.0'



## Runtime class modification

### Dependency
    implementation 'net.bytebuddy:byte-buddy:1.12.17'
    implementation 'net.bytebuddy:byte-buddy-agent:1.12.17'
