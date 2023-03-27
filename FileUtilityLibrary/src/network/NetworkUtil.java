package network;
import java.io.IOException;
import java.io.StringWriter;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import multithreading.ThreadUtil;

public class NetworkUtil {
	private static Logger logger = Logger.getLogger(NetworkUtil.class.toString());
	
	public static SocketChannel createTCPClientSocket(String address, int port) throws IOException {
		SocketAddress socketAddress = new InetSocketAddress(address, port);
		SocketChannel socketChannel = SocketChannel.open(socketAddress);
		
		return socketChannel;
	}
	
	public static DatagramChannel createUDPClientSocket(String address, int port) throws IOException {
		// SocketAddress socketAddress = new InetSocketAddress(address, port);
		DatagramChannel datagramChannel = DatagramChannel.open();
		datagramChannel.bind(null);
		// datagramChannel.bind(socketAddress);
		
		return datagramChannel;
	}	
	
	public static ServerSocketChannel createTCPServerSocket(int port) throws IOException {
		ServerSocketChannel channel = SelectorProvider.provider().openServerSocketChannel();
		channel.bind(new InetSocketAddress(port));
		return channel;
	}
	
	public static DatagramChannel createUDPServerSocket(int port) throws IOException {
		DatagramChannel channel = SelectorProvider.provider().openDatagramChannel();
		channel.bind(new InetSocketAddress(port));
		return channel;
	}	
	
	public static void connectClientTCP(int port) {
		try {
			
			SocketChannel clientChannel = NetworkUtil.createTCPClientSocket("127.0.0.1", port);
			
			final StringWriter stringWriter = new StringWriter();
			stringWriter.append("Message to send\n");
			
			byte messageInBytes[] = stringWriter.toString().getBytes();
			
			clientChannel.socket().getOutputStream().write(messageInBytes);
			
			String shutdownMessage = "SHUTDOWN";
			clientChannel.socket().getOutputStream().write(shutdownMessage.getBytes());
			
		} catch (IOException e) {
			logger.severe("Client Thread Exception: "+e.toString());
		}		
	}
	
	public static void setupServerTCP(ConcurrentLinkedQueue<String> sharedQueue, int port) {		
		Runnable serverRunnable = () -> {
				
				ServerSocketChannel tcpServer = null;					
				SocketChannel serverChannel = null;
				try {
					tcpServer = NetworkUtil.createTCPServerSocket(port);					
					
					boolean keepRunning = true;
					boolean messageReceived = true;
					while (keepRunning) {
						if (messageReceived) {
							serverChannel = tcpServer.accept();
							messageReceived = false;
						}
						
						int available = serverChannel.socket().getInputStream().available();
						
						if (available > 0) {
							messageReceived = true;
							byte bytesRead[] = new byte[available];
							serverChannel.socket().getInputStream().read(bytesRead);
							String messageRead = new String(bytesRead);
							logger.info("Message Read from Server Socket on port "+port+": "+messageRead);
							sharedQueue.add(messageRead);
							
							if (messageRead.indexOf("SHUTDOWN")>-1) {
								keepRunning = false;
							}
						}
					}
				} catch (IOException e) {
					logger.severe("Server Thread Exception: "+e.toString());
				} finally {
					if (null!=tcpServer) {
						try {
							tcpServer.close();
						} catch (IOException e) {
							logger.severe("Error closing Server: "+e.getMessage());
						}
					}
				}
				
				logger.info("Exiting Server thread");
				
			};
			
		Thread serverThread = new Thread(serverRunnable); 
		ThreadUtil.addTask(serverThread);
	}
	
	public static void connectClientUDP(String address, int port) {
		try {
			
			DatagramChannel clientChannel = NetworkUtil.createUDPClientSocket(address, port+1);
			
			final StringWriter stringWriter1 = new StringWriter();
			stringWriter1.append("Message to send test\n");
			
			byte messageInBytes1[] = stringWriter1.toString().getBytes();
			
			DatagramPacket dataPacket = new DatagramPacket(messageInBytes1, messageInBytes1.length);
			dataPacket.setSocketAddress(new InetSocketAddress(address,7777));
			clientChannel.socket().send(dataPacket);
			
			final StringWriter stringWriter2 = new StringWriter();
			stringWriter2.append("SHUTDOWN\n");
			
			byte messageInBytes2[] = stringWriter2.toString().getBytes();			
			
			DatagramPacket shutdownDataPacket = new DatagramPacket(messageInBytes2, messageInBytes2.length);
			shutdownDataPacket.setSocketAddress(new InetSocketAddress(address,7777));
			clientChannel.socket().send(shutdownDataPacket);
			
		} catch (BindException e) {
			logger.severe("Bind exception: "+e.toString());
	    } catch (IOException e) {
			logger.severe("Client Thread Exception: "+e.toString());
		}		
	}	
	
	public static void setupServerUDP(LinkedBlockingQueue<String> sharedQueue, int port) {		
		Runnable serverRunnable = () -> {
				
				DatagramChannel udpServer = null;					
				try {
					udpServer = NetworkUtil.createUDPServerSocket(port);					
					
					boolean keepRunning = true;
					
					ByteBuffer messageBuffer = ByteBuffer.allocate(1024);
					while (keepRunning) {
							udpServer.receive(messageBuffer);							
							String messageRead = new String(messageBuffer.array());
							int index = messageRead.indexOf('\n');
							messageRead = messageRead.substring(0,index);
							logger.info("Message Read from UDP Server Socket on port "+port+": "+messageRead);
							sharedQueue.add(messageRead);
							messageBuffer.clear();
						if (messageRead.indexOf("SHUTDOWN")>-1) {
							keepRunning = false;
						}
					}
				} catch (IOException e) {
					logger.severe("Server Thread Exception: "+e.toString());
				} finally {
					if (null!=udpServer) {
						try {
							udpServer.close();
						} catch (IOException e) {
							logger.severe("Error closing Server: "+e.getMessage());
						}
					}
				}
				
				logger.info("Exiting Server thread");
				
			};
			
		Thread serverThread = new Thread(serverRunnable); 
		ThreadUtil.addTask(serverThread);
	}	
}
