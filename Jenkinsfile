
properties([
   rateLimitBuilds(throttle: [count: 1000, durationName: 'hour', userBoost: true]) 
 ]
)


// options { disableConcurrentBuilds() }

//throttle(['hive-precommit']) {

lock(resource: 'some_resource', skipIfLocked: true) {
  echo 'Do something now or never!'
}

def ccLock(lock, n, block) {
  while(true) {
    for(int i=0;i<n;i++) {
      def lockName = lock + "_" + i;
      echo "Checking: ${lockName}" 
      lock(resource: 'lockName', skipIfLocked: true) {
        echo "Acquired: ${lockName}" 
        try {
          block();
        } finally {
          echo "Exiting: ${lockName}" 
          return;
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
