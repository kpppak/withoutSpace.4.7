import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Server {
    public static final int SERVER_PORT = 10101;

    public static void main(String[] args) throws IOException {
        final ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress("localhost", SERVER_PORT));

        while (true){
            try (SocketChannel socketChannel = serverChannel.accept()){
                final ByteBuffer inputBuffer = ByteBuffer.allocate(2<<10);

                while (socketChannel.isConnected()){
                    int bytesCount = socketChannel.read(inputBuffer);
                    if (bytesCount == -1) break;
                    final String msg = new String(inputBuffer.array(), 0,
                            bytesCount, StandardCharsets.UTF_8);
                    inputBuffer.clear();
                    System.out.println("Client's text received: " + msg);
                    ExtraTrim extraTrim = new ExtraTrim(msg);
                    String output = extraTrim.trimExtraSpaces();
                    socketChannel.write(ByteBuffer.wrap(("Server: \n" + output)
                            .getBytes(StandardCharsets.UTF_8)));
                }
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
