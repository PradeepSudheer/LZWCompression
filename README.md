# LZWCompression
ReadME.txt


LZW-Compression: 
The Lempel-Ziv-Welch algorithm is a lossless and adaptive compression algorithm that works without any prior assumption
of the data. The algorithm is most commonly used in file formats such as GIF and TIFF image formats. Encoding and Decoding are
the two steps involved in the algorithm. This algorithm doesn’t transmit the table or dictionary generated using the encoding or decoding steps.

Prerequisites : 
Implementation is carried out in JAVA language.
Java version: 7
JDK version: 1.8


Usage:
LZW-Compression algorithm is used to compress the data and decompress the data in a lossless manner.


Data structures used in the algorithm:
    HashMap is used in encoding the data with String as keys and integer as values.
    Lists are used to capture the intermediate data and writing to the files
    HashMap is used in decoding the compressed data with Integer as keys and string as values.
    
Flow of the files taken as input and generated in algorithm:
    
    input.txt --(encoding) ---> input.lzw --(decoding) ---> input_decoded.txt
    (encoding input)            (encoding output)            (decoding output)
                                (decoding input)
                                (compressed file)
    
    
Overview:
    Step1: Encoding:
        Inputs: Text file and bit_length are taken as inputs from the command prompt. bit_length is used to calculate the maximum size of the table.  
        Outputs: compressed file is returned as output and characters are represented in 16-bit binary format.
        Table for all the characters which are encountered in the file is maintained and used for encoding the data.
        Commands that are used to execute encoding.java:
                javac encoding.java
                java encoding.java filepath(input1.txt) n(bitlength)
        output file generated is in .lzw format(input1.lzw)

    Step2: Decoding:
        Inputs: Compressed file generated from the Encoding step is taken as input for this step and bit_length is taken as input. 
        Outputs: decompressed text file is generated as output.
        Table is maintained when traversing through the input file and is used during input processing and data is decoded.
        Commands that are used to execute decoding.java:
                javac decoding.java    
                java decoding.java filepath(input1.lzw) n(bitlength)
        output file generated is in .txt format(input1_decoded.txt)

    
RUN COMMANDS:
        Encoding:
            javac encoding.java
            java encoding.java filepath(input1.txt) n(bitlength)
        Decoding:
            javac decoding.java    
            java decoding.java filepath(input1.lzw) n(bitlength)
