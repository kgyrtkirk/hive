
properties([
   rateLimitBuilds(throttle: [count: 1000, durationName: 'hour', userBoost: true]) 
 ]
)


// options { disableConcurrentBuilds() }

//throttle(['hive-precommit']) {

lock(resource: 'some_resource', skipIfLocked: true) {
  echo 'Do something now or never!'
}

def ccLock(lockName, n, block) {
  while(true) {
    for(int i=0;i<n;i++) {
      def currentLockName = lockName + "_" + i;
      echo "Checking: ${currentLockName}" 
      lock(resource: currentLockName, skipIfLocked: true) {
        echo "Acquired: ${currentLockName}" 
        try {
          block();
        } finally {
          echo "Exiting: ${currentLockName}" 
          return
        }
      }
      sleep(10);
    }
  }
}

ccLock('hivePrecommit',2)  {
node {
//	proprtyird
// properties([rateLimitBuilds: 
//   properties([disableConcurrentBuilds()])

node {
//   properties([disableConcurrentBuilds()])
   sh "sleep 5"
}
   sh "sleep 5"
}

}
//}

echo 'exit'
