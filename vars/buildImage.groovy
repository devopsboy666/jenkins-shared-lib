def call(String imageName, String tag, String path) {
    try {
        sh "podman build -t ${imageName}:${tag} ${path}/."
    } catch (err) {
        echo "❌ Docker build failed: ${err.message}"
        error("Docker build failed")
    }
}