package application;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.control.ListView;


public class DiskTraverse {
	
	//final static Logger logging = LogManager.getLogger(DiskTraverse.class);
	int totalFiles,mp4FilesCount,aviFilesCount,txtFilesCount,mkvFilesCount,mp3FilesCount,webmFilesCount,exeFilesCount,zipFilesCount,jarFilesCount,isoFilesCount,miscFilesCount = 0;
	double totalSize,mp4Storage,aviStorage,txtStorage,mkvStorage,mp3Storage,webmStorage,exeStorage,zipStorage,jarStorage,isoStorage,miscStorage = 0;
	//need to find why the below line is not working
	//ArrayList<String> txtList,mkvList,mp3List,webmList,exeList,zipList,jarList,isoList,miscList,diskStats = new ArrayList<>();
	ArrayList<String> mp4List = new ArrayList<>();
	ArrayList<String> txtList = new ArrayList<>();
	ArrayList<String> mkvList = new ArrayList<>();
	ArrayList<String> aviList = new ArrayList<>();
	ArrayList<String> mp3List = new ArrayList<>();
	ArrayList<String> webmList = new ArrayList<>();
	ArrayList<String> exeList = new ArrayList<>();
	ArrayList<String> zipList = new ArrayList<>();
	ArrayList<String> jarList = new ArrayList<>();
	ArrayList<String> isoList = new ArrayList<>();
	ArrayList<String> miscList = new ArrayList<>();
	ArrayList<String> diskStats = new ArrayList<>();
	ListView<String> data;
	
	void traverse(File parentNode)
	{
		//logging.info("in Disk Traverse method");
		if (parentNode.isDirectory())
		{
			File [] directoryList = parentNode.listFiles();
			for (File file:directoryList)
			{
				if(file.isFile())
				{
					String fileName = file.getName();
					String fileType = null;
					if (fileName.indexOf(".")!=-1)
					fileType = fileName.substring(fileName.lastIndexOf("."));
					else 
						fileType = "unknown";
					double fileSize=((double)file.length()/(1024*1024));
					totalSize += fileSize;
					FileGroup(fileType, file, fileSize);
					//System.out.println("File Name: "+fileName +"File Type: "+fileType + "File Size: "+String.format("%.2f", fileSize));
					totalFiles++;
				}
				else 
				{
					traverse(file);
					//System.out.println("Subnode: "+file.getPath());
				}
			}
			//System.out.println("DiskTraverse completed");
		}
	}
	
	void FileGroup(String type, File file, double fileSize)
	{
		//logging.info("in FileGroup method");
		type = type.toLowerCase();
		String fileDetail= ("File Name: "+file.getName()+"\t\t\t\tFile Path: "+file.getAbsolutePath());
		switch(type) 
		{
		case ".mp4":
			mp4FilesCount ++;
			mp4Storage += fileSize;
			mp4List.add(fileDetail);
			break;
		case ".avi":
			aviFilesCount ++;
			aviStorage += fileSize;
			aviList.add(fileDetail);
			break;
		case ".txt":
			txtFilesCount ++;
			txtStorage += fileSize;
			txtList.add(fileDetail);
			break;
		case ".mkv":
			mkvFilesCount ++;
			mkvStorage += fileSize;
			mkvList.add(fileDetail);
			break;
		case ".mp3":
			mp3FilesCount ++;
			mp3Storage += fileSize;
			mp3List.add(fileDetail);
			break;
		case ".webm":
			webmFilesCount ++;
			webmStorage += fileSize;
			webmList.add(fileDetail);
			break;
		case ".exe":
			exeFilesCount ++;
			exeStorage += fileSize;
			exeList.add(fileDetail);
			break;
		case ".zip":
			zipFilesCount ++;
			zipStorage += fileSize;
			zipList.add(fileDetail);
			break;
		case ".jar":
			jarFilesCount ++;
			jarStorage += fileSize;
			jarList.add(fileDetail);
			break;
		case ".iso":
			isoFilesCount ++;
			isoStorage += fileSize;
			isoList.add(fileDetail);
			break;
		default:
			miscFilesCount ++;
			miscStorage += fileSize;
			miscList.add(fileDetail);
			break;
			
			
		}
	}
	
	ArrayList filesDetails()
	{
		diskStats.add("Total Number of Files: " +totalFiles);
		diskStats.add("Total Size of Files in GB: " +String.format("%.2f",(totalSize/1024)));
		diskStats.add("Total Number of mp4 Files: " +mp4FilesCount);
		diskStats.add("Total Number of avi Files: " +aviFilesCount);
		diskStats.add("Total Number of mkv Files: " +mkvFilesCount);
		diskStats.add("Total Number of mp3 Files: " +mp3FilesCount);
		diskStats.add("Total Number of webm Files: " +webmFilesCount);
		diskStats.add("Total Number of iso Files: " +isoFilesCount);
		diskStats.add("Total Number of exe Files: " +exeFilesCount);
		diskStats.add("Total Number of zip Files: " +zipFilesCount);
		diskStats.add("Total Number of jar Files: " +jarFilesCount);
		diskStats.add("Total Number of txt Files: " +txtFilesCount);
		diskStats.add("Total Number of misc Files: " +miscFilesCount);
		return diskStats;
	}
	
	ArrayList fileTypeStorageUsed()
	{
		double per;
		ArrayList<String> statsInPer = new ArrayList<>();
		per = (mp4Storage/totalSize)*100;
		statsInPer.add("MP4 Files:" +per);
		per = (aviStorage/totalSize)*100;
		statsInPer.add("AVI Files:" +per);
		per = (mkvStorage/totalSize)*100;
		statsInPer.add("MKV Files:" +per);
		per = (mp3Storage/totalSize)*100;
		statsInPer.add("MP3 Files:" +per);
		per = (webmStorage/totalSize)*100;
		statsInPer.add("WEBM Files:" +per);
		per = (isoStorage/totalSize)*100;
		statsInPer.add("ISO Files:" +per);
		per = (exeStorage/totalSize)*100;
		statsInPer.add("EXE Files:" +per);
		per = (zipStorage/totalSize)*100;
		statsInPer.add("ZIP Files:" +per);
		per = (jarStorage/totalSize)*100;
		statsInPer.add("JAR Files:" +per);
		per = (txtStorage/totalSize)*100;
		statsInPer.add("TXT Files:" +per);
		per = (miscStorage/totalSize)*100;
		statsInPer.add("MISC Files:" +per);
		return statsInPer;
	}
	
	ArrayList returnFileList(String type)
	{
		if (type.equals("mp4"))
			return mp4List;
		else if (type.equals("avi"))
			return aviList;
		else if(type.equals("mp3"))
			return mp3List;
		else if(type.equals("webm"))
			return webmList;
		else if(type.equals("mkv"))
			return mkvList;
		else if(type.equals("exe"))
			return exeList;
		else if(type.equals("iso"))
			return isoList;
		else if(type.equals("zip"))
			return zipList;
		else if(type.equals("jar"))
			return jarList;
		else if(type.equals("txt"))
			return txtList;
		else if(type.equals("misc"))
		return miscList;
		else
			return null;
	}

}
