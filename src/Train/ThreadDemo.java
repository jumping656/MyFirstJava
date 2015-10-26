package Train;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ejiping on 2015/10/26.
 */
public class ThreadDemo {
	public static void main(String[] args) {
		public List<String> getFileList (String dir){
			List<String> listFile = new ArrayList<>();
			File dirFile = new File(dir);
			//�������Ŀ¼�ļ�����ֱ�ӷ���
			if (dirFile.isDirectory()) {
				//����ļ����µ��ļ��б�Ȼ������ļ����ͷֱ���
				File[] files = dirFile.listFiles();
				if (null != files && files.length > 0) {
					//����ʱ������
					Arrays.sort(files, new Comparator<File>() {
						public int compare(File f1, File f2) {
							return (int) (f1.lastModified() - f2.lastModified());
						}

						public boolean equals(Object obj) {
							return true;
						}
					});
					for (File file : files) {
						//�������Ŀ¼��ֱ�����
						if (!file.isDirectory()) {
							listFile.add(file.getAbsolutePath());
						} else {
							//����Ŀ¼�ļ����ݹ����
							listFile.addAll(getFileList(file.getAbsolutePath()));
						}
					}
				}
			}
			return listFile;
		}

		String dir = "C:\\";
		String keyword = "system";
		List<String> file_list = new ArrayList<>();
		file_list = getFileList(keyword);

	}
}