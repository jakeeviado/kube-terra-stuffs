healthch-java

basic health checker (Spring Boot Actuator endpoint) application with full CI/CD pipeline


```mermaid

flowchart LR
    %% Node Definitions
    subgraph VCS["Source Control"]
        Repo["GitHub Repository"]
    end

    subgraph Phase1["1. CI: Verify Pull Request"]
        SCA["Static Code Analysis"]
        Tests["Unit & Mock Tests"]
        DepAudit["Dependency Audit"]

        SCA --> Tests
        Tests --> DepAudit
    end

    subgraph Phase2["2. Integrations & Packaging"]
        Integration["Integration Tests"]
        DockerBuild["Build Image\n(Dockerfile / Buildpacks)"]
    end

    subgraph Phase3["3. Security & Quality Gates"]
        ImageScan["Container Image Scan\n(Trivy / Grype)"]
    end

    subgraph Phase4["4. Registry Publishing"]
        Publish["Publish Tagged Image\nto Registry"]
    end

    subgraph Phase5["5. Terraform / CD"]
        Terraform["Apply Configs & Manifests"]
        K8sCluster["Kubernetes Cluster\n(Minikube / Local / Cloud)"]

        Terraform --> K8sCluster
    end

    %% Flow Connections
    Repo --> Phase1
    Phase1 --> Phase2
    Phase2 --> Phase3
    Phase3 --> Phase4
    Phase4 --> Phase5

```





