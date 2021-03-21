package io.wolverine.dns.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

import com.google.gson.Gson;

public class DefaultDnsResolver extends AbstractDnsResolver{

	private String get(String json) {
		String dnsIp = this.getDnsServerIp();
		if(dnsIp == null) return null;
		
        DatagramSocket socket = null;
        
        InetAddress address = null;
        DatagramPacket result = new DatagramPacket(new byte[512], 512, address, 1053);
        try {
        	socket = new DatagramSocket();
            address = InetAddress.getLocalHost();
            byte[] buf= json.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 1053);
            
            socket.send(packet);
            socket.receive(result);
            
            return result.getData().toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
        	if(socket != null)  socket.close();
        }
        
	}
	@Override
	protected String getHostFromRemote(String json) {
		String result = this.get(json);
		if(result == null) return null;
		Gson gson = new Gson();
		HostToIp hostToIp = gson.fromJson(result, HostToIp.class);
		if(hostToIp == null) {
			return null;
		}
		return hostToIp.getRecord();
	}

	@Override
	protected String getHostFromLocal(String hostname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<String> getHostListFromRemote(String json) {
		String result = this.get(json);
		if(result == null) return null;
		Gson gson = new Gson();
		HostToIpList hostToIpList = gson.fromJson(result, HostToIpList.class);
		if(hostToIpList == null ) {
			return null;
		}
		return hostToIpList.getRecords();
	}

	@Override
	protected List<String> getHostListFromLocal(String servicename) {
		// TODO Auto-generated method stub
		return null;
	}

}
