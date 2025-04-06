def call(String repoUrl, String branch) {
    try {
        sh "git clone -b ${branch} ${repoUrl}"
    } catch (err) {
        echo "❌ Git clone failed: ${err.message}"
        error("Git clone failed")
    }
}