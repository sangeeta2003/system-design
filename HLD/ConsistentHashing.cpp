#include <bits/stdc++.h>
using namespace std;

// Node represents a server in the cluster
struct Node {
    string id;
    int weight;

    // Constructor
    Node(const string& id_, int w) : id(id_), weight(w) {}

    // Default constructor (needed for std::map)
    Node() : id(""), weight(1) {}
};

// Consistent Hashing class
class ConsistentHashing {
private:
    map<long long, Node> ring;   // hash ring
    int virtualNodes;            // number of virtual nodes per physical node

    // Simple hash function for strings
    long long hashFunction(const string& key) {
        return std::hash<string>{}(key);
    }

public:
    ConsistentHashing(int vNodes = 3) : virtualNodes(vNodes) {}

    // Add a node (server) to the ring
    void addNode(const Node& node) {
        for (int i = 0; i < virtualNodes * node.weight; i++) {
            string vnode = node.id + "#" + to_string(i);
            long long hash = hashFunction(vnode);
            ring[hash] = node;
        }
    }

    // Remove a node from the ring
    void removeNode(const string& nodeId) {
        for (int i = 0; i < virtualNodes; i++) {
            string vnode = nodeId + "#" + to_string(i);
            long long hash = hashFunction(vnode);
            ring.erase(hash);
        }
    }

    // Get the node responsible for a key
    Node getNode(const string& key) {
        if (ring.empty()) throw runtime_error("No nodes available");

        long long hash = hashFunction(key);
        auto it = ring.lower_bound(hash);

        // Wrap around if at end
        if (it == ring.end()) return ring.begin()->second;

        return it->second;
    }

    // Print current hash ring
    void printRing() {
        cout << "Current Hash Ring:" << endl;
        for (auto &p : ring) {
            cout << p.first << " -> " << p.second.id << endl;
        }
        cout << endl;
    }
};

// ---------- MAIN FUNCTION ----------
int main() {
    ConsistentHashing ch(3);

    // Add servers
    ch.addNode(Node("ServerA", 1));
    ch.addNode(Node("ServerB", 2));
    ch.addNode(Node("ServerC", 1));

    ch.printRing();

    // Map some keys
    vector<string> keys = {"apple", "banana", "cherry", "date", "grape"};

    for (auto &key : keys) {
        Node node = ch.getNode(key);
        cout << "Key '" << key << "' is mapped to " << node.id << endl;
    }

    cout << "\nRemoving ServerB...\n";
    ch.removeNode("ServerB");
    ch.printRing();

    for (auto &key : keys) {
        Node node = ch.getNode(key);
        cout << "Key '" << key << "' is mapped to " << node.id << endl;
    }

    return 0;
}
