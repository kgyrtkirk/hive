
// options { disableConcurrentBuilds() }

throttle(['test_2']) {
node {
   properties([disableConcurrentBuilds()])
   sh "sleep 60"
}
}
