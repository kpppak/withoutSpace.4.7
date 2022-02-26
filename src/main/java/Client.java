import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static final int SERVER_PORT = 10101;

    public static void main(String[] args) throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", SERVER_PORT);
        final SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(socketAddress);

        try (Scanner scanner = new Scanner(System.in)){
            final ByteBuffer inputBuffer= ByteBuffer.allocate(2 << 10);
            String msg;
            while (true){
                System.out.println("\nEnter text to get rid of extra spaces:");
                msg = scanner.nextLine();
                if ("end".equals(msg)) break;
                socketChannel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
                int bytesCount = socketChannel.read(inputBuffer);
                System.out.println(new String(inputBuffer.array(), 0,
                        bytesCount, StandardCharsets.UTF_8).trim());
                inputBuffer.clear();
            }
        } finally {
            socketChannel.close();
        }
    }
}
