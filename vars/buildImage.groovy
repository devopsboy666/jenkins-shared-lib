def call(String imageName, String tag) {
    try {
        sh "docker build -t ${imageName}:${tag} ."
    } catch (err) {
        echo "❌ Docker build failed: ${err.message}"
        error("Docker build failed")
    }
}