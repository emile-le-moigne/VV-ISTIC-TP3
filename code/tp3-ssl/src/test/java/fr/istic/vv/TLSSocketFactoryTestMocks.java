package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TLSSocketFactoryTestMocks {

    @Test
    public void preparedSocket_NullProtocols() {

        SSLSocket socket = mock(SSLSocket.class);

        TLSSocketFactory factory = new TLSSocketFactory();

        when(socket.getSupportedProtocols()).thenReturn(null);
        when(socket.getEnabledProtocols()).thenReturn(null);
        doAnswer(fail()).when(socket).setEnabledProtocols(any(String[].class));

        factory.prepareSocket(socket);

        verify(socket, times(1)).getSupportedProtocols();
        verify(socket, times(1)).getEnabledProtocols();
        verify(socket, times(1)).setEnabledProtocols(any(String[].class));
    }

    @Test
    void typical() {

        SSLSocket socket = mock(SSLSocket.class);

        TLSSocketFactory f = new TLSSocketFactory();

        when(socket.getEnabledProtocols()).thenReturn(shuffle(new String[] { "SSLv3", "TLSv1" }));
        when(socket.getSupportedProtocols())
                .thenReturn(shuffle(new String[] { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2" }));
        doAnswer(invocation -> {
            String[] protocols = invocation.getArgument(0);
            assertArrayEquals(new String[] { "TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" }, protocols);
            return null;
        }).when(socket).setEnabledProtocols(any(String[].class));

        f.prepareSocket(socket);

        verify(socket, times(1)).getSupportedProtocols();
        verify(socket, times(1)).getEnabledProtocols();
        verify(socket, times(1)).setEnabledProtocols(any(String[].class));

    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }

}