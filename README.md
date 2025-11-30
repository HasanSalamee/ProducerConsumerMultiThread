# Parallel Text Processing System

## 📋 Overview

A multi-threaded text processing system using the Producer-Consumer pattern. This system reads text files, processes the content in parallel using multiple threads, and writes the results efficiently. Designed for optimal performance through concurrent processing.

## 🎯 Features

- ✅ Parallel file processing using multiple threads
- ✅ Producer-Consumer pattern with synchronized queues
- ✅ Thread-safe synchronization mechanisms
- ✅ Customizable thread count and queue capacity
- ✅ In-memory text processing
- ✅ Efficient resource management
- ✅ Comprehensive error handling and exception management
- ✅ Configurable timeouts and capacity limits

## 🏗️ Project Structure

```
ProducerConsumerMultiThread/
├── src/
│   ├── Main.java                 # Application entry point and user interface
│   ├── ProducerConsumer.java     # Main system coordinator
│   ├── Producer.java             # File reader component
│   ├── Consumer.java             # Data processor and writer
│   └── EventStorage.java         # Shared event storage with synchronized queues
├── input.txt                     # Sample input file
├── output.txt                    # Sample output file
└── README.md                     # Project documentation
```

## 📁 Class Descriptions

### 1. `Main.java`
- User interface for collecting input parameters
- System configuration setup
- Process initialization and monitoring

### 2. `ProducerConsumer.java`
- Thread pool management and coordination
- Orchestrates workflow between producer and consumers
- Process monitoring and timeout handling

### 3. `Producer.java`
- Reads input file line by line
- Adds lines to the first processing queue
- Signals system when reading is complete

### 4. `Consumer.java`
- **Processing Consumers**: Text processing and transformation
- **Final Consumer**: Writes processed results to output file
- Implements text stemming and normalization

### 5. `EventStorage.java`
- Manages synchronized blocking queues
- Coordinates thread synchronization
- Prevents race conditions with thread-safe operations

## 🚀 Getting Started

### Prerequisites
- Java JDK 8 or higher
- Operating system with Java support

### Installation & Execution

1. **Clone the repository**:
```bash
git clone https://github.com/HasanSalamee/ProducerConsumerMultiThread.git
cd ProducerConsumerMultiThread
```

2. **Compile the source code**:
```bash
javac -d bin src/*.java
```

3. **Run the application**:
```bash
java -cp bin Main
```

4. **Enter configuration parameters when prompted**:
   - Input file path
   - Output file path
   - Number of processing consumers
   - Queue capacity

### Direct Execution (without interactive input)
```bash
java -cp bin Main input.txt output.txt 4 100
```

## ⚙️ Configuration Parameters

| Parameter | Description | Default Value |
|-----------|-------------|---------------|
| Processing Consumers | Number of threads for text processing | 4 |
| Queue Capacity | Maximum items in each processing queue | 100 |
| Timeout | Maximum wait time for process completion | 10 minutes |

## 🔧 Usage Example

### Input File (`input.txt`)
```
Hello World
This is a sample text processing example
Multi-threaded system implementation
Java advanced programming
```

### Output File (`output.txt`)
```
hello world [processed]
this is a sample text processing example [processed]
multi-threaded system implementation [processed]
java advanced programming [processed]
```

## 🎨 Data Flow Architecture

```
[Producer] → [Queue 1] → [Multiple Consumers] → [Queue 2] → [Single Consumer]
    ↓             ↓              ↓                   ↓            ↓
  Read        Input          In-Memory           Output        Write
  File        Queue          Processing          Queue         File
```

## 📊 Performance Features

- **Parallel Processing**: Utilizes all available CPU cores
- **Bottleneck Prevention**: Configurable queue capacities
- **Memory Efficiency**: Stream processing line by line
- **Thread Optimization**: Balanced workload distribution

## 🐛 Troubleshooting

### Common Issues and Solutions:

1. **File Not Found**:
   - Verify file path correctness
   - Check read permissions

2. **Insufficient Memory**:
   - Reduce queue capacity
   - Decrease number of consumers

3. **Long Execution Time**:
   - Increase consumer threads
   - Expand queue capacity

4. **Thread Synchronization Issues**:
   - Ensure proper volatile variable usage
   - Verify interrupt handling

## 🔄 Processing Pipeline

1. **File Reading Phase**: Producer reads input file sequentially
2. **Text Processing Phase**: Multiple consumers process text concurrently
3. **Output Writing Phase**: Final consumer writes results to file
4. **Synchronization**: EventStorage manages inter-thread communication

## 🛠️ Build and Development

### Building from Source
```bash
# Compile all Java files
javac -d bin src/*.java

# Create JAR file (optional)
jar cfe ProducerConsumer.jar Main -C bin .
```

### Running Tests
```bash
# Create test input file
echo -e "Line 1\nLine 2\nLine 3" > test_input.txt

# Run with test data
java -cp bin Main test_input.txt test_output.txt 2 50
```

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. Fork the project
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Contribution Guidelines
- Follow Java coding conventions
- Include comments for complex logic
- Add appropriate exception handling
- Test with various input sizes

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Author

- **Hasan Salamee** - [GitHub Profile](https://github.com/HasanSalamee)

## 🙏 Acknowledgments

- Java Concurrency API for robust thread management
- Producer-Consumer pattern for efficient parallel processing
- BlockingQueue implementations for synchronized data exchange

## 📞 Support

If you encounter any issues:

1. Open an [Issue](https://github.com/HasanSalamee/ProducerConsumerMultiThread/issues)
2. Provide detailed problem description
3. Include error logs and configuration details
4. Specify Java version and operating system

## 📈 Performance Tips

- Optimal consumer count: Equal to number of CPU cores
- Queue capacity: Based on expected line length and memory
- For large files: Use larger queue capacity to prevent blocking
- Monitor system resources during execution

---

**Note**: This system demonstrates advanced multi-threading concepts in Java and can be extended for various text processing applications including natural language processing, data transformation, and batch processing workflows.

⭐ Star this repository if you find it helpful!
