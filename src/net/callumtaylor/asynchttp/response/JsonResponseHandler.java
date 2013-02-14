package net.callumtaylor.asynchttp.response;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public abstract class JsonResponseHandler extends AsyncHttpResponseHandler
{
	StringBuffer stringBuffer = new StringBuffer();

	@Override public void onPublishedDownloadProgress(byte[] chunk, int chunkLength, long totalProcessed, long totalLength)
	{
		if (chunk != null)
		{
			try
			{
				stringBuffer.append(new String(chunk, 0, chunkLength, "UTF-8"));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Processes the response from the stream.
	 * This is <b>not</b> ran on the UI thread
	 *
	 * @return The data represented as a gson JsonElement primitive type
	 */
	@Override public JsonElement getContent()
	{
		return new JsonParser().parse(new String(stringBuffer.toString()));
	}
}