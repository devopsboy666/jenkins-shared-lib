def call(String repoUrl, String branch) {
    try {
        echo "Step 1 Clone Repository......."
        sh "git clone -b ${branch} ${repoUrl}"

    } catch (err) {
        echo "❌ Git clone failed: ${err.message}"
        error("Git clone failed")
    }
}
