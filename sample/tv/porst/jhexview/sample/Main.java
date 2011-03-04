package tv.porst.jhexview.sample;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import tv.porst.jhexview.JHexView;
import tv.porst.jhexview.JHexView.DefinitionStatus;
import tv.porst.jhexview.SimpleDataProvider;

/**
 * Sample application that demonstrates a minimal use of the JHexView component.
 * @author sp
 *
 */
public final class Main {

	public static void main(final String[] args) {

		final JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(800, 600);

		final JHexView hexView = new JHexView();
		frame.add(hexView);

		final JMenuBar menuBar = new JMenuBar();

		final JMenu menu = new JMenu("File");
		menu.add(new AbstractAction("Open") {

			@Override
			public void actionPerformed(final ActionEvent arg0) {

				final JFileChooser chooser = new JFileChooser();

				if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(frame)) {

					final File file = chooser.getSelectedFile();

					try {
						hexView.setData(new SimpleDataProvider(readFile(file)));
						hexView.setDefinitionStatus(DefinitionStatus.DEFINED);
						hexView.setEnabled(true);
					} catch (final IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		menuBar.add(menu);

		frame.setJMenuBar(menuBar);

		frame.setVisible(true);
	}

	public static byte[] readFile(final File file) throws IOException {

		if (file == null) {
			throw new IllegalArgumentException("File argument must not be null");
		}

		final FileInputStream inputStream = new FileInputStream(file);
		final byte[] data = new byte[(int) file.length()];

		try {
			inputStream.read(data);
		}
		finally {
			inputStream.close();
		}

		return data;
	}
}
