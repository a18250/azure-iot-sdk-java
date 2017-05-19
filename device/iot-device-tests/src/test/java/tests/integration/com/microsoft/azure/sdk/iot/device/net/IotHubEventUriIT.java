// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package tests.integration.com.microsoft.azure.sdk.iot.device.net;

import com.microsoft.azure.sdk.iot.device.net.IotHubEventUri;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/** Integration tests for IotHubEventUri. */
public class IotHubEventUriIT
{
    @Test
    public void eventUriIsCorrect() throws URISyntaxException
    {
        String iotHubName = "test.iothub";
        String deviceId = "test-deviceid";
        IotHubEventUri uri = new IotHubEventUri(iotHubName, deviceId);

        String testUriStr = uri.toString();

        String expectedUriStr = "test.iothub/devices/test-deviceid/messages/events?api-version=2016-02-03";
        assertThat(testUriStr, is(expectedUriStr));
    }
}
