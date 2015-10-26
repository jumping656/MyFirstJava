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
			//如果不是目录文件，则直接返回
			if (dirFile.isDirectory()) {
				//获得文件夹下的文件列表，然后根据文件类型分别处理
				File[] files = dirFile.listFiles();
				if (null != files && files.length > 0) {
					//根据时间排序
					Arrays.sort(files, new Comparator<File>() {
						public int compare(File f1, File f2) {
							return (int) (f1.lastModified() - f2.lastModified());
						}

						public boolean equals(Object obj) {
							return true;
						}
					});
					for (File file : files) {
						//如果不是目录，直接添加
						if (!file.isDirectory()) {
							listFile.add(file.getAbsolutePath());
						} else {
							//对于目录文件，递归调用
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