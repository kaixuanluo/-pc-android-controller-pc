package main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import constants.Dimens;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.DirectMediaPlayerComponent;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.direct.BufferFormat;
import uk.co.caprica.vlcj.player.direct.BufferFormatCallback;
import uk.co.caprica.vlcj.player.direct.RenderCallback;
import uk.co.caprica.vlcj.player.direct.format.RV32BufferFormat;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class PlayerPanel extends JPanel {
	//
	private EmbeddedMediaPlayer mPlayer;
	private String mIp;
	private int mPort;
	
	private Canvas mCanvas;

	public PlayerPanel() {
		super();
	}

	public PlayerPanel(String ip, int port) {
		//
		// String[] VLC_ARGS = { "--intf", "dummy", // no interface
		// "--vout", "dummy", // we don't want video (output)
		// "--no-audio", // we don't want audio (decoding)
		// "--no-video-title-show", // nor the filename displayed
		// "--no-stats", // no stats
		// "--no-sub-autodetect-file", // we don't want subtitles
		// "--no-inhibit", // we don't want interfaces
		// "--no-disable-screensaver", // we don't want interfaces
		// "--no-snapshot-preview", // no blending in dummy vout
		// };

		this.mIp = ip;
		this.mPort = port;

		boolean found = new NativeDiscovery().discover();
		System.out.println("是否找到路径 " + found + " " + LibVlc.INSTANCE.libvlc_get_version());
		
//		 NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:\\kx");
		   
		MyEMPC playerComp = new MyEMPC();
		Canvas videoSurface = playerComp.getVideoSurface();
		mCanvas = videoSurface;
		videoSurface.setPreferredSize(new Dimension(Dimens.PHONE_WIDTH, Dimens.PHONE_HEIGHT));
		videoSurface.setBackground(Color.black);
		videoSurface.setVisible(true);
		playerComp.setVisible(true);
		mPlayer = playerComp.getMediaPlayer();
		this.setPreferredSize(new Dimension(Dimens.PHONE_WIDTH, Dimens.PHONE_HEIGHT));
		this.setVisible(true);
		this.add(playerComp);

		System.out.println("video canvas is playable " + videoSurface.isDisplayable());
		
//		player3();
	}
	
	public Canvas getPlayCanvas () {
		return mCanvas;
	}

	// onGetMediaPlayerFactoryArg

	private class MyEMPC extends EmbeddedMediaPlayerComponent {
		@Override
		protected String[] onGetMediaPlayerFactoryArgs() {
			// TODO Auto-generated method stub
			return new String[] { "--video-title=vlcj video output", "--no-snapshot-preview", "--quiet-synchro",
					"--sub-filter=logo:marq", "--intf=dummy", "--network-caching=10", "--file-caching=10",
					"--live-caching=10", "--clock-jitter=10",
					"--tcp-caching=10",
//					"--h264-fps=5",
					"--clock-synchro=1",//时钟同步
//					"--mosaic-delay=0"
//					"--sout-ts-shaping=0", "--sout-ts-dts-delay=0", 
//					"--sout-ts-pcr=0", "--sout-ts-use-key-frames"
					};
		}
//		 "--sout-ts-dts-delay=30"
	}

	public void playMedia() {

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (mPlayer != null) {
					String mrl = "tcp://"+mIp+":"+mPort;
					mPlayer.playMedia(mrl, new String[] { ":demux=h264" });
				}
			}
		}).start();

	}

	public void stopMedia() {
		if (mPlayer != null) {
			mPlayer.stop();
		}
	}

	// private static String formatHttpStream(String serverAddress, int
	// serverPort) {
	// StringBuilder sb = new StringBuilder(60);
	// sb.append(":sout=#duplicate{dst=std{access=http,mux=ts,");
	// sb.append("dst=");
	// sb.append(serverAddress);
	// sb.append(':');
	// sb.append(serverPort);
	// sb.append("}}");
	// return sb.toString();
	// }

//	private void player3() {
//
//		MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
//		EmbeddedMediaPlayer mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
//
//		Canvas canvas = new Canvas();
//		canvas.setBackground(Color.black);
//		CanvasVideoSurface videoSurface = mediaPlayerFactory.newVideoSurface(canvas);
//		mediaPlayer.setVideoSurface(videoSurface);
//
//		this.removeAll();
//		this.add(canvas);
//		this.setPreferredSize(new Dimension(Dimens.PHONE_WIDTH, Dimens.PHONE_HEIGHT));
//		this.setVisible(true);
//
//		System.out.println("video canvas is playable 2 " + canvas.isDisplayable());
//		
//		String mrl = "tcp://192.168.3.3:9005";
////		boolean booleanPlayer = mediaPlayer.playMedia(mrl, new String[] { ":demux=h264" });
////		System.out.println("是否播放 " + booleanPlayer);
//	}
}