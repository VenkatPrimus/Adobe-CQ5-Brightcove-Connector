/*
    Adobe CQ5 Brightcove Connector

    Copyright (C) 2011 Coresecure Inc.

        Authors:    Alessandro Bonfatti
                    Yan Kisen

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.brightcove.proserve.mediaapi.webservices;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.codec.binary.Base64;

@Component(	immediate=true,
			label="Brightcove Service",
            description="Brightcove Service Configuration",
            name="com.coresecure.brightcove.cq5.services.webservices.BrcServiceImpl",
            metatype = true
            )
@Service
@Properties({
	@Property(name="readtoken", label="Read Token", description="Read Token", value=""),
	@Property(name="writetoken", label="Write Token", description="Write Token", value=""),
    @Property(name="playersstore", label="Players Store Path", description="Path of the players store locatione", value="/content/brightcovetools/players"),
    @Property(name="defVideoPlayerID", label="Default Video Player ID", description="Default Video Player ID", value=""),
	@Property(name="defVideoPlayerKey", label="Default Video Player Key", description="Default Video Player Key", value=""),
	@Property(name="defPlaylistPlayerID", label="Default Playlist Player ID", description="Default Playlist Player ID", value=""),
	@Property(name="defPlaylistPlayerKey", label="Default Playlist Player Key", description="Default Playlist Player Key", value=""),
	@Property(name="previewPlayerLoc", label="Preview Video Player", description="Preview Player Path (Videos)", value="http://link.brightcove.com/services/player/bcpid1154829530001"),
	@Property(name="previewPlayerListLoc", label="Preview Playlist Player", description="Preview Player Path (Playlists)", value="http://link.brightcove.com/services/player/bcpid1154829529001")
})


public class BrcServiceImpl implements BrcService {
    private ComponentContext componentContext;
    private static Logger loggerVar = LoggerFactory.getLogger(BrcService.class);
    private static final String ALGO = "AES";
    private Dictionary<String, Object> prop;
    private Dictionary<String, Object> getProperties() {
        if (prop == null)
            return new Hashtable<String, Object>();
        return prop;
    }
    
    @Activate 
    void activate(ComponentContext aComponentContext) {
        this.componentContext=aComponentContext;
        this.prop = componentContext.getProperties();
    }

	public String getReadToken() {
		// TODO Auto-generated method stub
		return (String) getProperties().get("readtoken");
	}

	public String getWriteToken() {
		return (String) getProperties().get("writetoken");
	}
	
	public String getPreviewPlayerLoc() {
		// TODO Auto-generated method stub
		return (String) getProperties().get("previewPlayerLoc");
	}

	public String getPreviewPlayerListLoc() {
		return (String) getProperties().get("previewPlayerListLoc");
	}
	public String getPlayersLoc() {
		return (String) getProperties().get("playersstore");
	}
	public String getDefVideoPlayerID() {
		// TODO Auto-generated method stub
		return (String) getProperties().get("defVideoPlayerID");
	}

	public String getDefVideoPlayerKey() {
		return (String) getProperties().get("defVideoPlayerKey");
	}
	
	public String getDefPlaylistPlayerID() {
		// TODO Auto-generated method stub
		return (String) getProperties().get("defPlaylistPlayerID");
	}

	public String getDefPlaylistPlayerKey() {
		return (String) getProperties().get("defPlaylistPlayerKey");
	}

}
