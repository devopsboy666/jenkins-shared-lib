def call(String imageName, String tag) {
    try {
        sh "docker run -d --name ${imageName} ${imageName}:${tag}"
    } catch (err) {
        echo "❌ Docker run failed: ${err.message}"
        error("Docker run failed")
    }
}