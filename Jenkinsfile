/*  properties([
    rateLimitBuilds(throttle: [count: 600, durationName: 'day', userBoost: false])
	]
  )
*/
// options { disableConcurrentBuilds() }

//throttle(['hive-precommit']) {

def ccLock(lock, n, block) {
  while(true) {
    for(int i=0;i<n;i++) {
      def lockName = label + "_" + n;
      echo "Checking: ${lockName}" 
      lock(resource: lockName, skipIfLocked: true) {
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

      ccLock('hive-precommit',2)  {
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
