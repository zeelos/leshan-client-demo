package io.zeelos.leshan.client.demo;

import org.eclipse.leshan.client.request.ServerIdentity;
import org.eclipse.leshan.client.resource.BaseInstanceEnabler;
import org.eclipse.leshan.core.response.ReadResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Random;

public class MyLocation extends BaseInstanceEnabler {

    private static final Logger LOG = LoggerFactory.getLogger(MyLocation.class);

    private static final Random RANDOM = new Random();

    private float latitude;
    private float longitude;
    private float scaleFactor;
    private Date timestamp;

    public MyLocation() {
        this(null, null, 1.0f);
    }

    public MyLocation(Float latitude, Float longitude, float scaleFactor) {
        if (latitude != null) {
            this.latitude = latitude + 90f;
        } else {
            this.latitude = RANDOM.nextInt(180);
        }
        if (longitude != null) {
            this.longitude = longitude + 180f;
        } else {
            this.longitude = RANDOM.nextInt(360);
        }
        this.scaleFactor = scaleFactor;
        timestamp = new Date();
    }

    @Override
    public ReadResponse read(ServerIdentity identity, int resourceid) {
        LOG.info("Read on Location Resource " + resourceid);
        switch (resourceid) {
            case 0:
                return ReadResponse.success(resourceid, getLatitude());
            case 1:
                return ReadResponse.success(resourceid, getLongitude());
            case 5:
                return ReadResponse.success(resourceid, getTimestamp());
            default:
                return super.read(identity, resourceid);
        }
    }

    public void moveLocation(String nextMove) {
        switch (nextMove.charAt(0)) {
            case 'w':
                moveLatitude(1.0f);
                break;
            case 'a':
                moveLongitude(-1.0f);
                break;
            case 's':
                moveLatitude(-1.0f);
                break;
            case 'd':
                moveLongitude(1.0f);
                break;
        }
    }

    private void moveLatitude(float delta) {
        latitude = latitude + delta * scaleFactor;
        timestamp = new Date();
        fireResourcesChange(0, 5);
    }

    private void moveLongitude(float delta) {
        longitude = longitude + delta * scaleFactor;
        timestamp = new Date();
        fireResourcesChange(1, 5);
    }

    public float getLatitude() {
        return latitude - 90.0f;
    }

    public float getLongitude() {
        return longitude - 180.f;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
