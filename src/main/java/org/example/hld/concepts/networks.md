# Networks

## Definition

Networks enable communication between systems, devices, or services over physical or virtual connections. They encompass
protocols, architectures, and technologies that facilitate data transfer, ensuring reliability, security, and
performance in distributed systems.

---

## Why It Matters

Networks are the backbone of distributed systems, enabling scalability, low-latency communication, and fault tolerance.
Understanding network layers, protocols, and technologies like CDN, DNS, and API gateways is crucial for designing
efficient systems. In interviews, network knowledge is tested to evaluate your ability to optimize data flow, secure
communication, and handle challenges like video streaming or global content delivery.

---

## Key Concepts

### 1. Network Layers (OSI Model)

- **Definition**: The OSI model divides network communication into seven layers, each handling specific functions.
- **How It Works**:
    - **Physical (Layer 1)**: Transmits raw bits (e.g., cables, fiber).
    - **Data Link (Layer 2)**: Ensures reliable node-to-node transfer (e.g., Ethernet).
    - **Network (Layer 3)**: Routes packets (e.g., IP).
    - **Transport (Layer 4)**: Manages end-to-end communication (e.g., TCP, UDP).
    - **Session (Layer 5)**: Maintains connections.
    - **Presentation (Layer 6)**: Handles data formatting (e.g., encryption).
    - **Application (Layer 7)**: Provides user-facing services (e.g., HTTP, DNS).
- **Examples**: TCP/IP stack, HTTP over TCP.
- **Use Cases**: Designing network-aware applications, debugging connectivity.
- **Trade-offs**:
    - **Pros**: Modular design simplifies protocol development.
    - **Cons**: Layering adds latency.

### 2. Network Security

- **Definition**: Protects data in transit using encryption, authentication, and access control.
- **How It Works**:
    - **Encryption**: Uses TLS/SSL for secure communication.
    - **Authentication**: Verifies identities (e.g., OAuth, JWT).
    - **Firewalls**: Filter traffic to block unauthorized access.
    - **Intrusion Detection**: Monitors for attacks.
- **Examples**: HTTPS for web traffic, VPNs for private networks.
- **Use Cases**: Securing APIs, protecting user data.
- **Trade-offs**:
    - **Pros**: Ensures privacy and integrity.
    - **Cons**: Adds latency (e.g., TLS handshake).

### 3. Content Delivery Network (CDN)

- **Definition**: A distributed network of edge servers caching content closer to users.
- **How It Works**: Replicates static content (e.g., images, videos) across global points of presence (PoPs).
- **Examples**: Cloudflare, Akamai, Amazon CloudFront.
- **Use Cases**: Serving web assets, streaming media (e.g., Netflix).
- **Trade-offs**:
    - **Pros**: Reduces latency, offloads origin servers.
    - **Cons**: Cache invalidation complexity, costs.

### 4. Domain Name System (DNS)

- **Definition**: Translates domain names (e.g., example.com) to IP addresses.
- **How It Works**: Uses a hierarchy of servers (root, TLD, authoritative) for resolution.
- **Examples**: Google DNS (8.8.8.8), Cloudflare DNS (1.1.1.1).
- **Use Cases**: Web browsing, service discovery.
- **Trade-offs**:
    - **Pros**: Simplifies addressing, enables load balancing.
    - **Cons**: Propagation delays, DNS spoofing risks.

### 5. Communication Protocols

- **Definition**: Rules for data exchange between systems.
- **Examples**:
    - **HTTP/HTTPS**: Stateless web communication (HTTP/1.1, HTTP/2, HTTP/3).
    - **WebSocket**: Bidirectional, persistent connections for real-time apps.
    - **Server-Sent Events (SSE)**: Server pushes updates over HTTP.
    - **TCP**: Reliable, connection-oriented transport.
    - **UDP**: Connectionless, low-latency transport.
    - **QUIC**: UDP-based protocol for HTTP/3, reducing latency.
- **Use Cases**:
    - HTTP: Web APIs.
    - WebSocket: Chat apps.
    - UDP: Video streaming, DNS.
- **Trade-offs**:
    - **Pros**: TCP ensures reliability; UDP/QUIC reduces latency.
    - **Cons**: TCP has overhead; UDP may lose packets.

### 6. Communication Standards

- **REST**:
    - **Definition**: Stateless API design using HTTP methods.
    - **Use Cases**: Web APIs (e.g., Twitter API).
    - **Trade-offs**: Simple but may require multiple requests.
- **SOAP**:
    - **Definition**: XML-based protocol with strict standards.
    - **Use Cases**: Enterprise systems (e.g., banking).
    - **Trade-offs**: Robust but verbose.
- **GraphQL**:
    - **Definition**: Query language for flexible data retrieval.
    - **Use Cases**: Mobile apps (e.g., GitHub API).
    - **Trade-offs**: Flexible but complex backend.
- **gRPC**:
    - **Definition**: High-performance RPC using HTTP/2 and Protocol Buffers.
    - **Use Cases**: Microservices (e.g., Google services).
    - **Trade-offs**: Fast but requires client libraries.

### 7. Head-of-Line Blocking (HOL) in HTTP

- **Definition**: HTTP/1.1 requests block subsequent requests in a single TCP connection.
- **How It Works**: A slow request delays others.
- **Mitigation**: HTTP/2 (multiplexing), HTTP/3 (QUIC).
- **Use Cases**: Optimizing web performance.
- **Trade-offs**:
    - **Pros**: HTTP/2+ improves throughput.
    - **Cons**: HTTP/1.1 common in legacy systems.

### 8. Protocols for Video Transmission

- **Definition**: Protocols optimized for low-latency, high-quality video streaming.
- **Examples**:
    - **RTMP**: Real-time streaming (legacy, e.g., Adobe Flash).
    - **HLS (HTTP Live Streaming)**: Adaptive bitrate over HTTP.
    - **DASH**: Similar to HLS, used by YouTube.
    - **WebRTC**: Peer-to-peer, low-latency video (e.g., Zoom).
- **Use Cases**: Live streaming (Twitch), video calls (Google Meet).
- **Trade-offs**:
    - **Pros**: HLS/DASH adapt to bandwidth; WebRTC minimizes latency.
    - **Cons**: HLS/DASH adds buffering; WebRTC hard to scale.

### 9. Network Latency and Bandwidth

- **Definition**: Latency is the time for data to travel; bandwidth is the data transfer rate.
- **How It Works**: Latency affected by distance, protocol overhead; bandwidth by network capacity.
- **Examples**: CDN reduces latency; fiber optics increase bandwidth.
- **Use Cases**: Optimizing API response times, streaming.
- **Trade-offs**:
    - **Pros**: Low latency improves user experience.
    - **Cons**: High bandwidth infrastructure is costly.

### 10. Network Topologies

- **Definition**: Arrangement of network nodes (e.g., star, mesh, bus).
- **How It Works**:
    - **Star**: Central hub connects nodes (e.g., enterprise networks).
    - **Mesh**: Nodes interconnect, improving redundancy.
- **Examples**: AWS VPC (star), P2P networks (mesh).
- **Use Cases**: Designing resilient distributed systems.
- **Trade-offs**:
    - **Pros**: Mesh enhances fault tolerance.
    - **Cons**: Mesh increases complexity.

### 11. API Gateway

- **Definition**: A single entry point for APIs, handling routing, authentication, and rate limiting.
- **How It Works**: Routes requests to microservices, enforces policies.
- **Examples**: AWS API Gateway, Kong.
- **Use Cases**: Microservices, serverless apps.
- **Trade-offs**:
    - **Pros**: Simplifies client access, enhances security.
    - **Cons**: Potential bottleneck if not scaled.

### 12. Rate Limiting and Throttling

- **Definition**: Controls request rates to prevent abuse and ensure fairness.
- **How It Works**: Uses token bucket or leaky bucket algorithms.
- **Examples**: NGINX rate limiting, API Gateway quotas.
- **Use Cases**: Protecting APIs, managing traffic spikes.
- **Trade-offs**:
    - **Pros**: Prevents overload, ensures availability.
    - **Cons**: May reject legitimate requests.

### 13. Network Reliability and Fault Tolerance

- **Definition**: Ensures networks operate despite failures.
- **How It Works**: Uses redundancy, failover, and retry mechanisms.
- **Examples**: Multi-region DNS, redundant CDN PoPs.
- **Use Cases**: High-availability systems (e.g., Google).
- **Trade-offs**:
    - **Pros**: Improves uptime.
    - **Cons**: Increases infrastructure costs.

---

## Real-World Examples

- **Netflix**: Uses Open Connect CDN, HLS/DASH for streaming, QUIC for low latency.
- **Google**: Employs DNS load balancing, gRPC for microservices, HTTPS for security.
- **Zoom**: Uses WebRTC over UDP for video calls, rate limiting for stability.
- **Twitter**: Implements REST APIs, NGINX load balancing, WebSocket for real-time.

---

## Trade-offs

- **TCP vs. UDP/QUIC**: TCP reliable but slow; UDP/QUIC fast but less reliable.
- **REST vs. GraphQL/gRPC**: REST simple, GraphQL flexible, gRPC fast.
- **CDN vs. Origin**: CDN reduces latency but complicates invalidation.
- **Security vs. Performance**: TLS secures but adds latency.
- **Mesh vs. Star Topology**: Mesh resilient but complex.

---

## Important Notes

- **Protocol Selection**:
    - TCP for reliability (e.g., APIs), UDP/QUIC for low latency (e.g., streaming).
    - WebSocket/SSE for real-time, HTTP for stateless.
- **Security**:
    - Use HTTPS, rate limiting, and firewalls for public APIs.
- **CDN and DNS**:
    - CDNs for static content, DNS for load balancing.
- **Video Streaming**:
    - HLS/DASH for compatibility, WebRTC for low-latency calls.
- **Latency Optimization**:
    - Use CDNs, QUIC, and API gateways to reduce latency.
- **Interview Tip**:
    - Clarify latency, reliability, and scalability needs before choosing protocols or technologies.

---

## Interview Guidance

### Common Interview Questions

1. **Explain the OSI model and its relevance**:
    - Describe layers (e.g., HTTP at Application layer).

2. **Design a system using a CDN**:
    - Example: Video streaming service.

3. **Compare HTTP/2 vs. HTTP/3 and their impact**:
    - Discuss HOL blocking, QUIC benefits.

4. **How to secure network communication?**:
    - Explain TLS, rate limiting, API gateways.

### Tips for Interviews

- **Clarify Requirements**: Ask about latency, reliability, or scalability needs.
- **Use Examples**: Reference Netflix (CDN) or Zoom (WebRTC).
- **Draw Diagrams**: Show network flow (client → CDN → server).
- **Discuss Trade-offs**: Highlight protocol or technology pros/cons.
- **Handle Follow-ups**: Be ready for “How to handle DNS failures?” or “How does QUIC improve performance?”

---

## Sample Interview Problem: Design a Video Streaming Service’s Network Architecture

### Problem Statement

Design a network architecture for a video streaming service (e.g., Netflix) serving 100M users, with 1B streams/day,
requiring low-latency playback (<1s) and high availability.

### Approach

- **Functional Requirements**:
    - Stream video content to users.
    - Support adaptive bitrate playback.
- **Non-Functional Requirements**:
    - Scalability: Handle 1B streams/day (~12K QPS).
    - Low latency: <1s for stream start.
    - High availability: 99.9% uptime.
- **Capacity Estimation**:
    - Storage: 100K videos × 1GB = 100TB.
    - Throughput: 1B streams/day = ~12K QPS.
- **Solution Components**:
    - CDN: Cloudflare for caching videos.
    - Protocol: HLS for adaptive streaming, QUIC for low latency.
    - Database: S3 for video storage, DynamoDB for metadata.
    - Load Balancer: NGINX for API traffic.
    - API Gateway: AWS API Gateway for routing, rate limiting.

### Solution

- **Architecture**:
    - Clients request videos via HTTPS over HTTP/3 (QUIC).
    - DNS resolves to Cloudflare CDN for cached videos.
    - On cache miss, CDN fetches from S3.
    - API Gateway routes metadata requests to DynamoDB via NGINX.
    - HLS delivers adaptive bitrate streams.
- **Schema**:
    - DynamoDB: Table `videos` (`video_id`, `title`, `url`, `metadata`).
    - S3: Stores video files (`/videos/<video_id>.m3u8`).
- **Network Flow**:
    - DNS routes to nearest CDN PoP.
    - CDN serves HLS segments; misses go to S3.
    - API Gateway enforces rate limiting, routes to API servers.
- **Security**:
    - HTTPS for all traffic.
    - JWT for authentication via API Gateway.
    - Rate limiting to prevent abuse.
- **Scalability**:
    - Add CDN PoPs for global coverage.
    - Scale S3 and DynamoDB for storage/metadata.

- **Trade-offs**:
    - HLS: Broad compatibility but buffering latency.
    - QUIC: Reduces latency but not universally supported.
    - API Gateway: Simplifies routing but potential bottleneck.
- **Scalability**:
    - Add CDN PoPs for traffic spikes.
    - Use DynamoDB auto-scaling.
- **Follow-up Questions**:
    - **How to reduce latency?** Pre-cache videos, use QUIC.
    - **How to handle failures?** Multi-region S3, DNS failover.

- **Architecture Diagram**:
  ```mermaid
  graph TD
      A[Client] --> B[DNS]
      B --> C[Cloudflare CDN]
      C --> D[S3 Origin]
      A --> E[API Gateway]
      E --> F[Load Balancer]
      F --> G[API Server]
      G --> H[DynamoDB]
      C --> G
  ```

---

## Resources

- *Computer Networking: A Top-Down Approach* by Kurose and Ross
- System Design Primer: Networking (https://github.com/donnemartin/system-design-primer)
- Cloudflare Learning Center: CDN and DNS (https://www.cloudflare.com/learning/)