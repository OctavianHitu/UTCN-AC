// OpenCVApplication.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "common.h"
#include "iostream"
#include<queue>
#include <random>
using namespace std;


ifstream fin("W:\\AN3_sem2\\PI\\lab1\\OpenCVApplication-VS2019_OCV3411_basic\\reconstruct.txt");
void testOpenImage()
{
	char fname[MAX_PATH];
	while(openFileDlg(fname))
	{
		Mat src;
		src = imread(fname);
		imshow("image",src);
		waitKey();
	}
}

void testOpenImagesFld()
{
	char folderName[MAX_PATH];
	if (openFolderDlg(folderName)==0)
		return;
	char fname[MAX_PATH];
	FileGetter fg(folderName,"bmp");
	while(fg.getNextAbsFile(fname))
	{
		Mat src;
		src = imread(fname);
		imshow(fg.getFoundFileName(),src);
		if (waitKey()==27) //ESC pressed
			break;
	}
}

void testImageOpenAndSave()
{
	Mat src, dst;

	src = imread("Images/Lena_24bits.bmp", CV_LOAD_IMAGE_COLOR);	// Read the image

	if (!src.data)	// Check for invalid input
	{
		printf("Could not open or find the image\n");
		return;
	}

	// Get the image resolution
	Size src_size = Size(src.cols, src.rows);

	// Display window
	const char* WIN_SRC = "Src"; //window for the source image
	namedWindow(WIN_SRC, CV_WINDOW_AUTOSIZE);
	cvMoveWindow(WIN_SRC, 0, 0);

	const char* WIN_DST = "Dst"; //window for the destination (processed) image
	namedWindow(WIN_DST, CV_WINDOW_AUTOSIZE);
	cvMoveWindow(WIN_DST, src_size.width + 10, 0);

	cvtColor(src, dst, CV_BGR2GRAY); //converts the source image to a grayscale one

	imwrite("Images/Lena_24bits_gray.bmp", dst); //writes the destination to file

	imshow(WIN_SRC, src);
	imshow(WIN_DST, dst);

	printf("Press any key to continue ...\n");
	waitKey(0);
}

void testNegativeImage()
{
	char fname[MAX_PATH];
	while(openFileDlg(fname))
	{
		double t = (double)getTickCount(); // Get the current time [s]
		
		Mat src = imread(fname,CV_LOAD_IMAGE_GRAYSCALE);
		int height = src.rows;
		int width = src.cols;
		Mat dst = Mat(height,width,CV_8UC1);
		// Asa se acceseaaza pixelii individuali pt. o imagine cu 8 biti/pixel
		// Varianta ineficienta (lenta)
		for (int i=0; i<height; i++)
		{
			for (int j=0; j<width; j++)
			{
				uchar val = src.at<uchar>(i,j);
				uchar neg = MAX_PATH-val;
				dst.at<uchar>(i,j) = neg;
			}
		}

		// Get the current time again and compute the time difference [s]
		t = ((double)getTickCount() - t) / getTickFrequency();
		// Print (in the console window) the processing time in [ms] 
		printf("Time = %.3f [ms]\n", t * 1000);

		imshow("input image",src);
		imshow("negative image",dst);
		waitKey();
	}
}

void testParcurgereSimplaDiblookStyle()
{
	char fname[MAX_PATH];
	while (openFileDlg(fname))
	{
		Mat src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
		int height = src.rows;
		int width = src.cols;
		int w = src.step; // no dword alignment is done !!!
		Mat dst = src.clone();

		double t = (double)getTickCount(); // Get the current time [s]

		// the fastest approach using the “diblook style”
		uchar *lpSrc = src.data;
		uchar *lpDst = dst.data;
		for (int i = 0; i<height; i++)
			for (int j = 0; j < width; j++) {
				uchar val = lpSrc[i*w + j];
				lpDst[i*w + j] = 255 - val;
				/* sau puteti scrie:
				uchar val = lpSrc[i*width + j];
				lpDst[i*width + j] = 255 - val;
				//	w = width pt. imagini cu 8 biti / pixel
				//	w = 3*width pt. imagini cu 24 biti / pixel
				*/
			}

		// Get the current time again and compute the time difference [s]
		t = ((double)getTickCount() - t) / getTickFrequency();
		// Print (in the console window) the processing time in [ms] 
		printf("Time = %.3f [ms]\n", t * 1000);

		imshow("input image", src);
		imshow("negative image", dst);
		waitKey();
	}
}

void testColor2Gray()
{
	char fname[MAX_PATH];
	while(openFileDlg(fname))
	{
		Mat src = imread(fname);

		int height = src.rows;
		int width = src.cols;

		Mat dst = Mat(height,width,CV_8UC1);
		
		// Asa se acceseaaza pixelii individuali pt. o imagine RGB 24 biti/pixel
		// Varianta ineficienta (lenta)
		for (int i=0; i<height; i++)
		{
			for (int j=0; j<width; j++)
			{
				Vec3b v3 = src.at<Vec3b>(i,j);
				uchar b = v3[0];
				uchar g = v3[1];
				uchar r = v3[2];
				dst.at<uchar>(i,j) = (r+g+b)/3;
			}
		}
		
		imshow("input image",src);
		imshow("gray image",dst);
		waitKey();
	}
}

void testBGR2HSV()
{
	char fname[MAX_PATH];
	while (openFileDlg(fname))
	{
		Mat src = imread(fname);
		int height = src.rows;
		int width = src.cols;
		int w = src.step; // latimea in octeti a unei linii de imagine
		
		Mat dstH = Mat(height, width, CV_8UC1);
		Mat dstS = Mat(height, width, CV_8UC1);
		Mat dstV = Mat(height, width, CV_8UC1);
		
		// definire pointeri la matricele (8 biti/pixeli) folosite la afisarea componentelor individuale H,S,V
		uchar* dstDataPtrH = dstH.data;
		uchar* dstDataPtrS = dstS.data;
		uchar* dstDataPtrV = dstV.data;

		Mat hsvImg;
		cvtColor(src, hsvImg, CV_BGR2HSV);
		// definire pointer la matricea (24 biti/pixeli) a imaginii HSV
		uchar* hsvDataPtr = hsvImg.data;

		for (int i = 0; i<height; i++)
		{
			for (int j = 0; j<width; j++)
			{
				int hi = i*width * 3 + j * 3;
				// sau int hi = i*w + j * 3;	//w = 3*width pt. imagini 24 biti/pixel
				int gi = i*width + j;
				
				dstDataPtrH[gi] = hsvDataPtr[hi] * 510/360;		// H = 0 .. 255
				dstDataPtrS[gi] = hsvDataPtr[hi + 1];			// S = 0 .. 255
				dstDataPtrV[gi] = hsvDataPtr[hi + 2];			// V = 0 .. 255
			}
		}

		imshow("input image", src);
		imshow("H", dstH);
		imshow("S", dstS);
		imshow("V", dstV);
		waitKey();
	}
}

void testResize()
{
	char fname[MAX_PATH];
	while(openFileDlg(fname))
	{
		Mat src;
		src = imread(fname);
		Mat dst1,dst2;
		//without interpolation
		resizeImg(src,dst1,320,false);
		//with interpolation
		resizeImg(src,dst2,320,true);
		imshow("input image",src);
		imshow("resized image (without interpolation)",dst1);
		imshow("resized image (with interpolation)",dst2);
		waitKey();
	}
}

void testCanny()
{
	char fname[MAX_PATH];
	while(openFileDlg(fname))
	{
		Mat src,dst,gauss;
		src = imread(fname,CV_LOAD_IMAGE_GRAYSCALE);
		int k = 0.4;
		int pH = 50;
		int pL = k*pH;
		GaussianBlur(src, gauss, Size(5, 5), 0.8, 0.8);
		Canny(gauss,dst,pL,pH,3);
		imshow("input image",src);
		imshow("canny",dst);
		waitKey();
	}
}

void testVideoSequence()
{
	VideoCapture cap("Videos/rubic.avi"); // off-line video from file
	//VideoCapture cap(0);	// live video from web cam
	if (!cap.isOpened()) {
		printf("Cannot open video capture device.\n");
		waitKey();
		return;
	}
		
	Mat edges;
	Mat frame;
	char c;

	while (cap.read(frame))
	{
		Mat grayFrame;
		cvtColor(frame, grayFrame, CV_BGR2GRAY);
		Canny(grayFrame,edges,40,100,3);
		imshow("source", frame);
		imshow("gray", grayFrame);
		imshow("edges", edges);
		c = cvWaitKey();  // waits a key press to advance to the next frame
		if (c == 27) {
			// press ESC to exit
			printf("ESC pressed - capture finished\n"); 
			break;  //ESC pressed
		};
	}
}


void testSnap()
{
	VideoCapture cap(0); // open the deafult camera (i.e. the built in web cam)
	if (!cap.isOpened()) // openenig the video device failed
	{
		printf("Cannot open video capture device.\n");
		return;
	}

	Mat frame;
	char numberStr[256];
	char fileName[256];
	
	// video resolution
	Size capS = Size((int)cap.get(CV_CAP_PROP_FRAME_WIDTH),
		(int)cap.get(CV_CAP_PROP_FRAME_HEIGHT));

	// Display window
	const char* WIN_SRC = "Src"; //window for the source frame
	namedWindow(WIN_SRC, CV_WINDOW_AUTOSIZE);
	cvMoveWindow(WIN_SRC, 0, 0);

	const char* WIN_DST = "Snapped"; //window for showing the snapped frame
	namedWindow(WIN_DST, CV_WINDOW_AUTOSIZE);
	cvMoveWindow(WIN_DST, capS.width + 10, 0);

	char c;
	int frameNum = -1;
	int frameCount = 0;

	for (;;)
	{
		cap >> frame; // get a new frame from camera
		if (frame.empty())
		{
			printf("End of the video file\n");
			break;
		}

		++frameNum;
		
		imshow(WIN_SRC, frame);

		c = cvWaitKey(10);  // waits a key press to advance to the next frame
		if (c == 27) {
			// press ESC to exit
			printf("ESC pressed - capture finished");
			break;  //ESC pressed
		}
		if (c == 115){ //'s' pressed - snapp the image to a file
			frameCount++;
			fileName[0] = NULL;
			sprintf(numberStr, "%d", frameCount);
			strcat(fileName, "Images/A");
			strcat(fileName, numberStr);
			strcat(fileName, ".bmp");
			bool bSuccess = imwrite(fileName, frame);
			if (!bSuccess) 
			{
				printf("Error writing the snapped image\n");
			}
			else
				imshow(WIN_DST, frame);
		}
	}

}

void MyCallBackFunc(int event, int x, int y, int flags, void* param)
{
	//More examples: http://opencvexamples.blogspot.com/2014/01/detect-mouse-clicks-and-moves-on-image.html
	Mat* src = (Mat*)param;
	if (event == CV_EVENT_LBUTTONDOWN)
		{
			printf("Pos(x,y): %d,%d  Color(RGB): %d,%d,%d\n",
				x, y,
				(int)(*src).at<Vec3b>(y, x)[2],
				(int)(*src).at<Vec3b>(y, x)[1],
				(int)(*src).at<Vec3b>(y, x)[0]);
		}
}

void testMouseClick()
{
	Mat src;
	// Read image from file 
	char fname[MAX_PATH];
	while (openFileDlg(fname))
	{
		src = imread(fname);
		//Create a window
		namedWindow("My Window", 1);

		//set the callback function for any mouse event
		setMouseCallback("My Window", MyCallBackFunc, &src);

		//show the image
		imshow("My Window", src);

		// Wait until user press some key
		waitKey(0);
	}
}
/// <summary>
/// CERINTE LABORATOR 1
/// </summary>
void negative_image()
{
	Mat img = imread("Images/kids.bmp", IMREAD_GRAYSCALE);
	
	for (int i = 0; i < img.rows; i++)
		for (int j = 0; j < img.cols; j++)
		{
			img.at<uchar>(i, j) = 255 - img.at<uchar>(i, j);
		}
	imshow("negative img", img);
	waitKey(0);
}

void greyleveladitiv()
{

	int level = 20;
	Mat img = imread("Images/kids.bmp", IMREAD_GRAYSCALE);

	for (int i = 0; i < img.rows; i++)
		for (int j = 0; j < img.cols; j++)
		{
			img.at<uchar>(i, j) = (level + img.at<uchar>(i, j)) % 255;
		}
	imshow("aditiv img", img);
	waitKey(0);

}
void greylevelmulti()
{
	int level = 50;
	Mat img = imread("Images/kids.bmp", IMREAD_GRAYSCALE);

	for (int i = 0; i < img.rows; i++)
		for (int j = 0; j < img.cols; j++)
		{
			img.at<uchar>(i, j) = (level * img.at<uchar>(i, j)) % 255;
		}
	imshow("multi img", img);
	waitKey(0);


}

void fourcadrecolor()
{
	float h = 256;
	float w = 256;
	Mat img4color(h, w, CV_8UC3);
	for (int i = 0; i < h; i++)
	{
		for (int j = 0; j < w; j++)
		{
			if (i < h / 2)
			{
				if (j < w / 2)
				{
					img4color.at<Vec3b>(i,j)= Vec3b(255, 255, 255);
				}
				else
				{
					img4color.at<Vec3b>(i, j) = Vec3b(0, 0, 255);
				}

			}
			else
			{
				if (j < w / 2)
				{
					img4color.at<Vec3b>(i, j) = Vec3b(0, 255, 0);
				}
				else
				{
					img4color.at<Vec3b>(i, j) = Vec3b(0, 255, 255);
				}
			}
		}
	}
	imshow("img 4 color", img4color);
	waitKey(0);
}

void createmat()
{
	float nr[9];
	for (int i = 0; i < 9; i++) {
		scanf("%f", &nr[i]);
	}

	Mat M(3, 3, CV_32FC1, nr);
	cout << "Intial matrix" << endl << M << endl<<"-----------------"<<endl;
	system("pause");


	Mat M2 = M.inv();
	cout << "New Matrix " << endl << M2;
	system("pause");
}

void testfct()
{
	float h = 256;
	float w = 256;
	Mat img4color(h, w, CV_8UC3);
	for (int i = 0; i < h; i++)
	{
		for (int j = 0; j < w; j++)
		{
			img4color.at<Vec3b>(i, j) = Vec3b(255/2,255/2, 255/2);
		
		}
	}

	imshow("img 4 color", img4color);
	waitKey(0);

}

/// <summary>
/// FINAL CERINTE LABORATOR 1
/// </summary>

/// <summary>
/// CERINTE  LABORATOR 2
/// </summary>


void lab2pb1()
{
	int h, w;
	char fname[MAX_PATH];
	while (openFileDlg(fname))
	{
		Mat img = imread(fname,IMREAD_COLOR);
		h = img.rows;
		w = img.cols;
		Mat mat1= Mat(h, w, CV_8UC1);
		Mat mat2 = Mat(h, w, CV_8UC1);
		Mat mat3 = Mat(h, w, CV_8UC1);
		
		for (int i = 0; i < h; i++)
		{
			for (int j = 0; j < w; j++)
			{
				Vec3b pixel = img.at< Vec3b>(i, j);
				unsigned char B = pixel[0];
				unsigned char G = pixel[1];
				unsigned char R = pixel[2];
				mat1.at<uchar>(i, j) = R;
				mat2.at<uchar>(i, j) = G;
				mat3.at<uchar>(i, j) = B;

			}
		}
		imshow("img", img);
		imshow("img 1", mat1);
		imshow("img 2", mat2);
		imshow("img 3", mat3);
		waitKey(0);
	}

}

void lab2pb2()
{
	int h, w;
	char fname[MAX_PATH];
	while (openFileDlg(fname))
	{
		Mat img = imread(fname, IMREAD_COLOR);
		h = img.rows;
		w = img.cols;
		Mat mat1 = Mat(h, w, CV_8UC1);


		for (int i = 0; i < h; i++)
		{
			for (int j = 0; j < w; j++)
			{
				Vec3b pixel = img.at< Vec3b>(i, j);
				unsigned char B = pixel[0];
				unsigned char G = pixel[1];
				unsigned char R = pixel[2];
				mat1.at<uchar>(i, j) = (R+G+B)/3;


			}
		}
		imshow("img", img);
		imshow("img 1", mat1);
		waitKey(0);
	}

}

void lab2pb3()
{
	int treshhold;
	cout << "Scrie valoarea de treshhold:";
	cin >> treshhold;

	int h, w;
	char fname[MAX_PATH];
	while (openFileDlg(fname))
	{
		Mat img = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
		h = img.rows;
		w = img.cols;
		Mat mat1 = Mat(h, w, CV_8UC1);


		for (int i = 0; i < h; i++)
		{
			for (int j = 0; j < w; j++)
			{
				if (img.at<uchar>(i, j) < treshhold)
				{
					mat1.at<uchar>(i, j) = 0;
				}
				else
				{
					mat1.at<uchar>(i, j) = 255;
				}


			}
		}
		imshow("img", img);
		imshow("img 1", mat1);
		waitKey(0);
	}

}


void lab2pb4()
{
	//Adăugați la framework o funcție care convertește canalele R, G, B ale unei imagini
		//RGB24(tip CV_8UC3) în componente H, S, V folosind ecuațiile din 2.6.Memorați
		//componente H, S, V în câte o matrice de tip CV_8UC1 corespunzătoare canalelor H, S,
		//V.Afișați aceste matrice în 3 ferestre diferite.Verificați corectitudinea implementării prin
		//comparație vizuală cu rezultatele de mai jos.
	int h, w;
	char fname[MAX_PATH];
	
	while (openFileDlg(fname))
	{
		Mat img = imread(fname, IMREAD_COLOR);
		h = img.rows;
		w = img.cols;
		Mat mat1 = Mat(h, w, CV_8UC1);
		Mat mat2 = Mat(h, w, CV_8UC1);
		Mat mat3 = Mat(h, w, CV_8UC1);

		for (int i = 0; i < h; i++)
		{
			for (int j = 0; j < w; j++)
			{
				Vec3b color_pixel = img.at<Vec3b>(i, j);

				float b = (float)color_pixel[0] / 255;
				float g = (float)color_pixel[1] / 255;
				float r = (float)color_pixel[2] / 255;

				float M = max(max(r, g), b);
				float m = min(min(r, g), b);
				float C = M - m;

				float V = 0, H = 0, S = 0;

				V = M;

				if (V != 0) {
					S = C / V;
				}
				else {
					S = 0;
				}

				if (C != 0) {
					if (M == r)
						H = 60 * (g - b) / C;
					if (M == g)
						H = 120 + 60 * (b - r) / C;
					if (M == b)
						H = 240 + 60 * (r - g) / C;
				}
				else
					H = 0;

				if (H < 0)
					H = H + 360;

				mat1.at<uchar>(i, j) = (H / 360.0f) * 255.0f; 
				mat2.at<uchar>(i, j) = S * 255.0f;
				mat3.at<uchar>(i, j) = V * 255.0f;

			}
		}


		imshow("img", img);
		imshow("img 1", mat1);
		imshow("img 2", mat2);
		imshow("img 3", mat3);
		waitKey(0);
	}

}



bool isInside(int r, int c, const Mat& img) {
	return r >= 0 && r <= img.rows && c >= 0 && c <= img.cols;
}

void lab2pb5() {
	char fname[MAX_PATH];
	int i = 0, j = 0;
	while (openFileDlg(fname))
	{
		Mat img = imread(fname, IMREAD_COLOR);
		printf("Introduce i:");
		scanf("%d", &i);
		printf("Introduce j:");
		scanf("%d", &j);
		bool result = isInside(i, j, img);

		if (result) {
			printf("Pixel (%d,%d) is inside the image.\n", i, j);
		}
		else {
			printf("Pixel (%d,%d) is outside the image.\n", i, j);
		}

		imshow("Input Image", img);
		waitKey();
	}
}

/// <summary>
/// FINAL CERINTE LAB 2
/// </summary>
/// <returns></returns>

/// <summary>
/// CERINTE LAB 3
/// </summary>
/// <returns></returns>

/* Histogram display function - display a histogram using bars (simlilar to L3 / PI)
Input:
name - destination (output) window name
hist - pointer to the vector containing the histogram values
hist_cols - no. of bins (elements) in the histogram = histogram image width
hist_height - height of the histogram image
Call example:
showHistogram ("MyHist", hist_dir, 255, 200);
*/

void showHistogram(const std::string& name, int* hist, const int  hist_cols, const int hist_height)
{
	Mat imgHist(hist_height, hist_cols, CV_8UC3, CV_RGB(255, 255, 255)); // constructs a white image

	//computes histogram maximum
	int max_hist = 0;
	for (int i = 0; i < hist_cols; i++)
		if (hist[i] > max_hist)
			max_hist = hist[i];
	double scale = 1.0;
	scale = (double)hist_height / max_hist;
	int baseline = hist_height - 1;

	for (int x = 0; x < hist_cols; x++) {
		Point p1 = Point(x, baseline);
		Point p2 = Point(x, baseline - cvRound(hist[x] * scale));
		line(imgHist, p1, p2, CV_RGB(255, 0, 255)); // histogram bins colored in magenta
	}

	imshow(name, imgHist);
}

void lab3histograma()
{
	char fname[MAX_PATH];
	int list[256] = { 0 };
	float FDP[256] = { 0.0 };

	while (openFileDlg(fname))
	{
		Mat src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
		for (int i = 0; i < src.rows;i++)
		{
			for (int j = 0; j < src.cols; j++)
			{
				uchar  g = src.at<uchar>(i, j);
				list[g] = list[g] + 1;
			}

		}
		int M = src.rows * src.cols;
		for (int i = 0; i < 256; i++)
		{
			FDP[i] = (float)list[i] / M;
			printf("%f\n", FDP[i]);
		}
		showHistogram("My List", list, src.rows, src.cols);
		waitKey();
		
	}
}
void lab3pb4()
{
	int m = 0;
	char fname[MAX_PATH];
	
	while (openFileDlg(fname))
	{
		Mat src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
		printf("Intoduce M-ul mai mic decat 256:");
		scanf("%d", &m);


		int* x;
		x = (int*)calloc(m, sizeof(int));
		for (int i = 0; i < src.rows; i++)
		{
			for (int j = 0; j < src.cols; j++)
			{
				uchar g = src.at<uchar>(i, j);
				g /= m;
				x[g]++;
				
			}
			

		}


		showHistogram("My List", x, m, src.rows);
		imshow("input", src);
		waitKey();

	}
}

int WH = 5;
int TH = 0.0003f;

void lab3pb5()
{
	char fname[MAX_PATH];
	int list[256] = { 0 };
	float FDP[256] = { 0.0 };
	
	while (openFileDlg(fname))
	{
		Mat src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
		Mat dst = Mat(src.rows, src.cols, CV_8UC1);
		for (int i = 0; i < src.rows; i++)
		{
			for (int j = 0; j < src.cols; j++)
			{
				uchar  g = src.at<uchar>(i, j);
				list[g] = list[g] + 1;
			}

		}

		int M = src.rows * src.cols;
		for (int i = 0; i < 256; i++)
		{
			FDP[i] = (float)list[i] / M;
			printf("%f\n", FDP[i]);
		}

		vector <uchar> vf, mijloace;
		vf.push_back(0);
		mijloace.push_back(0);

		for (int i = WH; i <= 255 - WH; i++)
		{
			float medie = 0.0;
			int max = 1;//true
			for (int j = 0; j < 2 * WH + 1; j++)
			{
				medie += FDP[i + j - WH];
				if (FDP[i + j - WH] > FDP[i])
				{
					max = 0;//false/nu e maxim
				}
			}
			medie = medie / (2 * WH + 1);
			if (max && FDP[i] > medie + TH)
			{
				vf.push_back(i);
			}
		}
		vf.push_back(255);
		

		for (int i = 0; i < src.rows; i++)
		{
			for (int j = 0; j < src.cols; j++)
			{
				uchar current = src.at<uchar>(i, j);
				for (int k = 0; k < vf.size(); k++)
				{
					if (current >= vf[k] && current <= vf[k + 1])
					{
						if (current - vf[k] < vf[k + 1] - current)
							dst.at<uchar>(i, j) = vf[k];
						else
							dst.at<uchar>(i, j) = vf[k + 1];
					}

				}
			}
		}
		showHistogram("MyHist", list, src.rows, src.cols);
		imshow("multi level image", dst);
		imshow("initial image", src);
		waitKey();



	}

}



void lab3pb6()
{
	char fname[MAX_PATH];
	int list[256] = { 0 };
	float FDP[256] = { 0.0 };

	while (openFileDlg(fname))
	{
		Mat src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
		Mat dst = Mat(src.rows, src.cols, CV_8UC1);
		for (int i = 0; i < src.rows; i++)
		{
			for (int j = 0; j < src.cols; j++)
			{
				uchar  g = src.at<uchar>(i, j);
				list[g] = list[g] + 1;
			}

		}

		int M = src.rows * src.cols;
		for (int i = 0; i < 256; i++)
		{
			FDP[i] = (float)list[i] / M;
			//printf("%f\n", FDP[i]);
		}

		vector <uchar> vf, mijloace;
		vf.push_back(0);
		mijloace.push_back(0);

		for (int i = WH; i <= 255 - WH; i++)
		{
			float medie = 0.0;
			int max = 1;//true
			for (int j = 0; j < 2 * WH + 1; j++)
			{
				medie += FDP[i + j - WH];
				if (FDP[i + j - WH] > FDP[i])
				{
					max = 0;//false/nu e maxim
				}
			}
			medie = medie / (2 * WH + 1);
			if (max && FDP[i] > medie + TH)
			{
				vf.push_back(i);
			}
		}
		vf.push_back(255);


		for (int i = 0; i < src.rows; i++)
		{
			for (int j = 0; j < src.cols; j++)
			{
				uchar current = src.at<uchar>(i, j);
				for (int k = 0; k < vf.size(); k++)
				{
					if (current >= vf[k] && current <= vf[k + 1])
					{
						if (current - vf[k] < vf[k + 1] - current)
							dst.at<uchar>(i, j) = vf[k];
						else
							dst.at<uchar>(i, j) = vf[k + 1];
					}

				}
			}
		}

		for (int i = 0; i < src.rows-1; i++)
		{
			for (int j = 0; j < src.cols-1; j++)
			{
				uchar oldpixel = src.at<uchar>(i, j);
				uchar newpixel = dst.at<uchar>(i, j);
				src.at<uchar>(i, j) = newpixel;
				float error = oldpixel - newpixel;

				if (isInside(i, j+1, src))
				{
					src.at<uchar>(i, j + 1) = src.at<uchar>(i, j + 1) + 7 * error / 16;
				}
				if (isInside(i+1, j - 1, src))
				{
					src.at<uchar>(i+1, j - 1) = src.at<uchar>(i+1, j - 1) + 3 * error / 16;
				}
				if (isInside(i+1, j, src))
				{
					src.at<uchar>(i+1, j ) = src.at<uchar>(i+1, j ) + 5 * error / 16;
				}
				if (isInside(i+1, j + 1, src))
				{
					src.at<uchar>(i+1, j + 1) = src.at<uchar>(i+1, j + 1) +  error / 16;
				}

			}
		}
		
		showHistogram("MyHist", list, src.rows, src.cols);
		imshow("multi level image", dst);
		imshow("initial image", src);
		waitKey();

	}


}
/// <summary>
/// FINAL CERINTE LAB 3
/// </summary>

/// <summary>
/// CERINTE LAB 4
/// </summary>
/// 

int lab4_area(Vec3b pxl, Mat src)
{
	int area = 0;
	for (int i = 0; i < src.rows; i++)
	{
		for (int j = 0; j < src.cols; j++)
		{
			Vec3b currentpixel = src.at<Vec3b>(i, j);
			if (pxl[0] == currentpixel[0] && pxl[1] == currentpixel[1] && pxl[2] == currentpixel[2])
			{
				area++;
			}

		}
	}
	return area;
}

void lab4centermass(Vec3b pxl, Mat src, float* x, float* y)
{
	float area = lab4_area(pxl, src);
	int ax=0, ay = 0;

	for (int i = 0; i < src.rows; i++)
	{
		for (int j = 0; j < src.cols; j++)
		{
			Vec3b currentpixel = src.at<Vec3b>(i, j);
			if (pxl[0] == currentpixel[0] && pxl[1] == currentpixel[1] && pxl[2] == currentpixel[2])
			{
				ax += i;
				ay += j;
			}
		}
	}
	*x = ax / area;
	*y = ay / area;

}

float lab4_alongaxis(Vec3b pxl, Mat src)
{
	float cx = 0, cy = 0;
	lab4centermass(pxl, src, &cx, &cy);
	int num = 0;
	int dn1 = 0, dn2 = 0,dn=0;

	for (int i = 0; i < src.rows; i++)
	{
		for (int j = 0; j < src.cols; j++)
		{
			Vec3b currentpixel = src.at<Vec3b>(i, j);
			if (pxl[0] == currentpixel[0] && pxl[1] == currentpixel[1] && pxl[2] == currentpixel[2])
			{
				num += (i - cx) * (j - cy);
				dn1 += (j - cy) * (j - cy);
				dn2 += (i - cx) * (i - cx);

			}

		}
	}
	num *= 2;
	dn = dn1 - dn2;
	if (num == 0 && dn == 0)
	{
		printf("Symetrical\n");
		return 0;
	}
	else
	{
		return atan2(num, dn) / 2;
	}

}

bool econtur(Mat img, Vec3b pixel, int i, int j) {
	if (isInside(i+1, j + 1, img) && img.at<Vec3b>(i + 1, j + 1) != pixel)
		return true;
	if (isInside(i, j+1, img) && img.at<Vec3b>(i, j + 1) != pixel)
		return true;
	if (isInside(i - 1, j + 1, img) && img.at<Vec3b>(i - 1, j + 1) != pixel)
		return true;
	if (isInside(i - 1,j,img ) && img.at<Vec3b>(i - 1, j) != pixel)
		return true;
	if (isInside(i - 1,j-1 , img) && img.at<Vec3b>(i - 1, j - 1) != pixel)
		return true;
	if (isInside(i, j-1, img) && img.at<Vec3b>(i, j - 1) != pixel)
		return true;
	if (isInside(i+1, j- 1, img) && img.at<Vec3b>(i + 1, j - 1) != pixel)
		return true;
	if (isInside(i+1, j, img) && img.at<Vec3b>(i + 1, j) != pixel)
		return true;
	return false;

}

float lab4_perim(Vec3b pxl, Mat *src)
{
	int perim = 0;
	
	for (int i = 0; i < src->rows; i++)
	{
		for (int j = 0; j < src->cols; j++)
		{
			Vec3b currentpixel = src->at<Vec3b>(i, j);
			if (pxl[0] == currentpixel[0] && pxl[1] == currentpixel[1] && pxl[2] == currentpixel[2])
			{
				if (econtur(*src, currentpixel, i, j))
				{
					perim++;
				}

			}

		}
	}

	

	return perim;
}

float lab4_subfact(float area, int perim)
{
	float p2 = perim * perim;
	return 4 * PI * area / p2;
}


void lab4_proj(Vec3b pxl, Mat src)
{
	int h = src.rows;
	int w = src.cols;
	int* h1 = (int*)calloc(h, sizeof(int));
	int* w1 = (int*)calloc(w, sizeof(int));

	Mat_<Vec3b> proiectie(h, w, Vec3b(0, 0, 0));

	for (int i = 0; i < h; i++)
		for (int j = 0; j < w; j++) 
		{
			Vec3b curpxl = src.at<Vec3b>(i, j);
			if (pxl[0] == curpxl[0] && pxl[1] == curpxl[1] && pxl[2] == curpxl[2])
			{
				h1[i] += 1;
				w1[j] += 1;
			}
		}
	for (int i = 0; i < h; i++)
	{
		for (int j = 0; j < h1[i]; j++)
		{
			proiectie(i, j) = pxl;
		}
	}

	for (int j = 0; j < w; j++)
	{
		for (int i = 0; i < w1[j]; i++)
		{
			proiectie(i, j) = pxl;
		}
	}

	imshow("proiectie:", proiectie);
	free(h1);
	free(w1);
}


void lab4onmouse(int event, int x, int y, int flags, void* parametru)
{
	Mat* src = (Mat*)parametru;

	if (event == EVENT_LBUTTONDOWN)
	{
		Mat dstcopy = src->clone();
		Vec3b pixel_color = src->at<Vec3b>(y, x);

		printf("\n\nPos(x,y): %d,%d  Color(RGB): %d,%d,%d\n",
			x, y,
			pixel_color[2],
			pixel_color[1],
			pixel_color[0]);

		int area = lab4_area(pixel_color, *src);
		printf("Area is : %d\n", area);

		float cmx = 0;
		float cmy = 0;
		lab4centermass(pixel_color, *src, &cmx, &cmy);
		printf("Center of mass is : (%.2f,%.2f)\n", cmy, cmx);

		float ung = lab4_alongaxis(pixel_color, *src);
		printf("Axis of elongation(phi): %.2f\n", ung);

		int perimetru = lab4_perim(pixel_color, src);
		printf("Perimeter is: %d\n", perimetru);
		printf("Thinness ratio: %.2f\n", lab4_subfact(area, perimetru));

		lab4_proj(pixel_color, *src);

		circle(dstcopy, Point(cmy, cmx), 3, (255, 0, 0));
		line(dstcopy, Point(cmy, cmx), Point(cmy + 50 * cos(ung), cmx + 50 * sin(ung)), (255, 0, 0), 1);
		line(dstcopy, Point(cmy, cmx), Point(cmy - 50 * cos(ung), cmx - 50 * sin(ung)), (255, 0, 0), 1);
		imshow("New Window", dstcopy);

		waitKey(0);
	}
}


void lab4MouseClick()
{
	Mat src;
	// Read image from file 
	char fname[MAX_PATH];
	while (openFileDlg(fname))
	{
		src = imread(fname);
		//Create a window
		namedWindow("My Window", 1);

		//set the callback function for any mouse event
		setMouseCallback("My Window", lab4onmouse, &src);

		//show the image
		imshow("My Window", src);

		// Wait until user press some key
		waitKey(0);
	}
}
//
//FINAL LAB 4
//


//
//LABORATOR 5
//

void vecini8()
{
	uchar  label = 0;

	int dj[8] = { -1, 0, 1, -1, 1, -1, 0, 1 };
	int di[8] = { -1, -1, -1, 0, 0, 1, 1, 1 };

	int di4[4] = { -1,-0,1,0 };
	int dj4[4] = { 0,-1,0,1 };

	char fname[MAX_PATH];
	while (openFileDlg(fname)) {
		Mat src = imread(fname, IMREAD_GRAYSCALE);
		Mat labels = Mat::zeros(src.rows, src.cols, CV_8UC1);
		Mat dst(src.rows, src.cols, CV_8UC3, Scalar(255, 255, 255));
		for (int i = 1; i < src.rows - 1; i++) {
			for (int j = 1; j < src.cols - 1; j++) {

				if ((src.at<uchar>(i, j) == 0) && (labels.at<uchar>(i, j) == 0)) {
					label++;
					queue<Point> que;
					labels.at<uchar>(i, j) = label;
					que.push({ i, j });
					while (!que.empty()) {
						Point pnt = que.front();
						que.pop();
						for (int k = 0; k < 8; k++) {
							if ((src.at<uchar>(pnt.x + di[k], pnt.y + dj[k]) == 0) &&
								(labels.at<uchar>(pnt.x + di[k], pnt.y + dj[k]) == 0)) {
								labels.at<uchar>(pnt.x + di[k], pnt.y + dj[k]) = label;
								que.push({ pnt.x + di[k], pnt.y + dj[k] });
							}
						}
					}
				}
			}
		}
		imshow("labels", labels);


		default_random_engine gen;
		uniform_int_distribution<int> d(0, 255);
		for (int k = 1; k <= label; k++) {
			uchar red = d(gen);
			uchar blue = d(gen);
			uchar green = d(gen);
			Vec3b color = Vec3b(blue, green, red);
			for (int i = 1; i < src.rows - 1; i++) {
				for (int j = 1; j < src.cols - 1; j++) {
					if (labels.at<uchar>(i, j) == k)
						dst.at<Vec3b>(i, j) = color;
				}
			}
		}
		imshow("coloured", dst);
		waitKey(0);
	}
}

void vecini4()
{
	uchar  label = 0;



	int di[4] = { -1,-0,1,0 };
	int dj[4] = { 0,-1,0,1 };

	char fname[MAX_PATH];
	while (openFileDlg(fname)) {
		Mat src = imread(fname, IMREAD_GRAYSCALE);
		Mat labels = Mat::zeros(src.rows, src.cols, CV_8UC1);
		Mat dst(src.rows, src.cols, CV_8UC3, Scalar(255, 255, 255));
		for (int i = 1; i < src.rows - 1; i++) {
			for (int j = 1; j < src.cols - 1; j++) {

				if ((src.at<uchar>(i, j) == 0) && (labels.at<uchar>(i, j) == 0)) {
					label++;
					queue<Point> que;
					labels.at<uchar>(i, j) = label;
					que.push({ i, j });
					while (!que.empty()) {
						Point pnt = que.front();
						que.pop();
						for (int k = 0; k < 4; k++) {
							if ((src.at<uchar>(pnt.x + di[k], pnt.y + dj[k]) == 0) &&
								(labels.at<uchar>(pnt.x + di[k], pnt.y + dj[k]) == 0)) {
								labels.at<uchar>(pnt.x + di[k], pnt.y + dj[k]) = label;
								que.push({ pnt.x + di[k], pnt.y + dj[k] });
							}
						}
					}
				}
			}
		}
		imshow("labels", labels);


		default_random_engine gen;
		uniform_int_distribution<int> d(0, 255);
		for (int k = 1; k <= label; k++) {
			uchar red = d(gen);
			uchar blue = d(gen);
			uchar green = d(gen);
			Vec3b color = Vec3b(blue, green, red);
			for (int i = 1; i < src.rows - 1; i++) {
				for (int j = 1; j < src.cols - 1; j++) {
					if (labels.at<uchar>(i, j) == k)
						dst.at<Vec3b>(i, j) = color;
				}
			}
		}
		imshow("coloured", dst);
		waitKey(0);
	}
}

void lab5pb1()
{
	int m;
	printf("Doriti cu vecinaatte de 4 sau 8?");
	scanf("%d", &m);
	if (m == 4)
	{
		vecini4();
	}
	if(m==8)
	{
		vecini8();
	}
	waitKey(0);
}



void lab5pb3()
{
	Mat src;
	char fname[MAX_PATH];
	while (openFileDlg(fname)) {
		src = imread(fname, IMREAD_GRAYSCALE);
		int label = 0;
		Mat labels(src.rows, src.cols, CV_32FC1, Scalar(0));
		Mat dst(src.rows, src.cols, CV_8UC3, Scalar(0));

		int di[4] = { -1, -1, -1,  0 };
		int dj[4] = { -1,  0,  1, -1 };
		map<int, vector<int>> edges;
		for (int i = 0; i < src.rows; i++)
		{
			for (int j = 0; j < src.cols; j++)
			{

				if (src.at<uchar>(i, j) == 0 && labels.at<int>(i, j) == 0) {
					
						vector<int> L;
						for (int k = 0; k <= 3; k++)
						{
							int ii = i + di[k];
							int jj = j + dj[k];

							if (isInside(ii, jj, src))
							{
								if (labels.at<int>(ii, jj) > 0) {
									L.push_back(labels.at<int>(ii, jj));
								}
							}
						}

						if (L.empty())
						{
							labels.at<int>(i, j) = label;
							label++;
							
						}
						else
						{

							int x = *min_element(L.begin(), L.end());
							labels.at<int>(i, j) = x;
							for_each(L.begin(), L.end(), [&x, &edges](int y) {
								if (x != y) {
									if (edges.find(x) == edges.end())
										edges.emplace(x, vector<int>{});
									edges[x].push_back(y);
									if (edges.find(x) == edges.end())
										edges.emplace(y, vector<int>{});
									edges[y].push_back(x);
								}
								});
						}
					
				}
			}
		}



		for (int k = 1; k <= label; k++) {
			uchar red = rand() % 256;
			uchar blue = rand() % 256;
			uchar green = rand() % 256;
			Vec3b color = Vec3b(blue, green, red);
			for (int i = 1; i < src.rows; i++) {
				for (int j = 1; j < src.cols; j++) {
					if (labels.at<int>(i, j) == k)
						dst.at<Vec3b>(i, j) = color;
				}
			}
		}

		imshow("sursa", src);
		imshow("intermediar", dst);


		int newLabel = 0;
		vector<int> newLabels(label + 1);
		fill(newLabels.begin(), newLabels.end(), 0);
		Mat dst2(src.rows, src.cols, CV_8UC3, Scalar(0));

		for (int i = 0; i < label; i++)
		{
			if (newLabels[i] == 0)
			{
				newLabel++;
				newLabels[i] = newLabel;
				queue<int> Q;
				Q.push(i);

				while (!Q.empty())
				{
					int x = Q.front();
					Q.pop();
					for_each(edges[x].begin(), edges[x].end(), [&newLabel, &newLabels, &Q](int y) {
						if (newLabels[y] == 0) {
							newLabels[y] = newLabel;
							Q.push(y);
						}
						});
				}
			}
		}

		for (int i = 0; i < src.rows; i++) {
			for (int j = 0; j < src.cols; j++) {
				labels.at<int>(i, j) = newLabels[labels.at<int>(i, j)];
			}
		}

		for (int k = 1; k <= label; k++) {
			uchar red = rand() % 256;
			uchar blue = rand() % 256;
			uchar green = rand() % 256;
			Vec3b color = Vec3b(blue, green, red);
			for (int i = 1; i < src.rows; i++) {
				for (int j = 1; j < src.cols; j++) {
					if (labels.at<int>(i, j) == k)
						dst2.at<Vec3b>(i, j) = color;
				}
			}
		}

		imshow("final", dst2);
		waitKey(0);


	}
	

}
/////LAB 5 FINAL

////LAB 6 INCEPUT

//algortmul de urmarire a conturului
void lab6pb1_2()
{
	Mat src;
	char fname[MAX_PATH];
	while (openFileDlg(fname)) 
	{
		src = imread(fname, IMREAD_GRAYSCALE);
		Mat dst(src.rows, src.cols, CV_8UC1, Scalar(255));
		Point p0, actual, p1, prev;
		bool hv = false;
		for (int i = 0; i < src.rows; i++)
		{
			for (int j = 0; j < src.cols; j++)
			{
				if (src.at<uchar>(i, j) == 0)
				{
					p0.x = j;
					p0.y = i;
					std::cout << "PUNCT DE START:" << p0.x << " " << p0.y << '\n';
					hv = true;
					break;
				}
			}
			if (hv)
			{
				break;
			}
		}
		actual = p0;
		int di[8] = { 0,-1,-1,-1,0,1,1,1 };
		int dj[8] = { 1,1,0,-1,-1,-1,0,1 };
		int dir = 7;
		int n = 0;
		
		std::vector<int> ac, dc;
		do {
			n++;
			if (dir % 2 == 0)
			{
				dir = (dir + 7) % 8;
			}
			else
			{
				dir = (dir + 6) % 8;
			}

			while (src.at<uchar>(actual.y + di[dir], actual.x + dj[dir]) == 255)
			{
				dir = (dir + 1) % 8;
			}

			if (n == 1)
			{
				p1.x = p0.x + dj[dir];
				p1.y = p0.y + di[dir];
				actual = p1;
			}
			else
			{
				prev = actual;
				actual.x = actual.x + dj[dir];
				actual.y = actual.y + di[dir];
			}
			ac.push_back(dir);
			dst.at<uchar>(actual.y, actual.x) = 0;
			
		} while (!((actual == p1) && (prev == p0) && (n >= 2)));

		for (int i = 0; i < n - 1; i++)
		{
			dc.push_back((ac[i + 1] - ac[i] + 8) % 8);
		}

		printf(" CODUL INLANTUIT: \n");
		for (int i = 0; i < n - 1; i++)
		{
			
			printf(" %d ", ac[i]);
		}
		printf("\n CODUL DERIVAT: \n");
		for (int i = 0; i < n - 1; i++)
		{
			printf(" %d ", dc[i]);
			
		}

		imshow("contour image", dst);
		imshow("initial image", src);
		waitKey();
	}


}
//reconstructie
void lab6pb3()
{

	int n, x, y;
	int di[8] = { 0,-1,-1,-1,0,1,1,1 };
	int dj[8] = { 1,1,0,-1,-1,-1,0,1 };
	Mat dst(1000, 1000, CV_8UC1, Scalar(255));
	fin >> y;
	fin >> x;
	fin >> n;
	Point act = Point(x, y);
	dst.at<uchar>(act.y, act.x) = 0; int dir = 0;
	for (int i = 0; i < n; i++)
	{
		fin >> dir;
		act = Point(act.x + dj[dir], act.y + di[dir]);
		dst.at<uchar>(act.y, act.x) = 0;
	}
	imshow("CONTUR IMAGINE", dst);
	waitKey();


}

//LAB 6 FINAL

//LAB 7

 void lab7dilatare()
{
	 int n;

	 printf("De cate ori?\n");
	 scanf("%d", &n);
	 char fname[MAX_PATH];
	 while (openFileDlg(fname))
	 {
		Mat src = imread(fname, IMREAD_GRAYSCALE);
		Mat src1 = imread(fname, IMREAD_GRAYSCALE);
		Mat dst(src.rows, src.cols, CV_8UC1, Scalar(255));
		dst = src.clone();
		int di[8] = { 0,-1,-1,-1,0,1,1,1 };
		int dj[8] = { 1,1,0,-1,-1,-1,0,1 };
		for (int z = 0; z < n; z++)
		{
			for (int i = 1; i < src.rows - 1; i++)
			{
				for (int j = 1; j < src.cols - 1; j++)
				{
					if (src.at<uchar>(i, j) == 0)
					{
						//vecinatate 4
						/*dst.at<uchar>(i, j - 1) = 0;
						dst.at<uchar>(i - 1, j) = 0;
						dst.at<uchar>(i + 1, j) = 0;
						dst.at<uchar>(i, j + 1) = 0;
						dst.at<uchar>(i, j) = 0;*/

						//vecintate 8
						for (int y = 0; y < 8; y++)
						{
							dst.at<uchar>(i + dj[y], j + di[y]) = 0;
						}

					}
				}

			}
			dst.copyTo(src);
		}
		imshow("sursa", src1);
		imshow("destinatie", dst);
		waitKey();

	}
}

 void lab7eroziune()
 {
	 int n;
	 printf("De cate ori?\n");
	 scanf("%d",&n);
	 char fname[MAX_PATH];
	 while (openFileDlg(fname))
	 {
		 Mat src = imread(fname, IMREAD_GRAYSCALE);
		 Mat src1 = imread(fname, IMREAD_GRAYSCALE);
		 Mat dst(src.rows, src.cols, CV_8UC1, Scalar(255));
		 //dst = src.clone();
		 int di[8] = { 0,-1,-1,-1,0,1,1,1 };
		 int dj[8] = { 1,1,0,-1,-1,-1,0,1 };
		 for (int z = 0; z < n; z++)
		 {
			 for (int i = 1; i < src.rows - 1; i++)
			 {
				 for (int j = 1; j < src.cols - 1; j++)
				 {
					 if (src.at<uchar>(i, j) == 0)
					 {

						 bool gasit = true;
						 for (int y = 0; y < 8; y++)
						 {
							 if (src.at<uchar>(i + dj[y], j + di[y]) != 0)
							 {
								 gasit = false;
							 }

						 }
						 if (gasit) {
							 dst.at<uchar>(i, j) = 0;
						 }

					 }
				 }

			 }
			 dst.copyTo(src);
		 }

		 imshow("sursa", src1);
		 imshow("destinatie", dst);
		 waitKey();

	 }
 }

 Mat dilatare(Mat src)
 {

	 
	 
		 Mat dst(src.rows, src.cols, CV_8UC1, Scalar(255));
		 dst = src.clone();
		 int di[8] = { 0,-1,-1,-1,0,1,1,1 };
		 int dj[8] = { 1,1,0,-1,-1,-1,0,1 };

		 
			 for (int i = 1; i < src.rows - 1; i++)
			 {
				 for (int j = 1; j < src.cols - 1; j++)
				 {
					 if (src.at<uchar>(i, j) == 0)
					 {
						 //vecinatate 4
						 /*dst.at<uchar>(i, j - 1) = 0;
						 dst.at<uchar>(i - 1, j) = 0;
						 dst.at<uchar>(i + 1, j) = 0;
						 dst.at<uchar>(i, j + 1) = 0;
						 dst.at<uchar>(i, j) = 0;*/

						 //vecintate 8
						 for (int y = 0; y < 8; y++)
						 {
							 dst.at<uchar>(i + dj[y], j + di[y]) = 0;
						 }

					 }
				 }

			 }
		 
		 return dst;

 }

 Mat eroziune(Mat src)
 {
	 
		 
		 Mat dst(src.rows, src.cols, CV_8UC1, Scalar(255));
		 
		 int di[8] = { 0,-1,-1,-1,0,1,1,1 };
		 int dj[8] = { 1,1,0,-1,-1,-1,0,1 };

		 for (int i = 1; i < src.rows - 1; i++)
		 {
			 for (int j = 1; j < src.cols - 1; j++)
			 {
				 if (src.at<uchar>(i, j) == 0)
				 {
					
					 bool gasit = true;
					 for (int y = 0; y < 8; y++)
					 {
						 if (src.at<uchar>(i + dj[y], j + di[y]) != 0)
						 {
							 gasit = false;
						 }

					 }
					 if (gasit) {
						 dst.at<uchar>(i, j) = 0;
					 }
					 

				 }
			 }

		 }

		 return dst;

	 
 }



 void lab7deschidere()
 {
	 int n;

	 printf("De cate ori?\n");
	 scanf("%d", &n);
	 char fname[MAX_PATH];
	 while (openFileDlg(fname))
	 {
		 Mat src = imread(fname, IMREAD_GRAYSCALE);
		 Mat src1 = imread(fname, IMREAD_GRAYSCALE);
		 Mat dst(src.rows, src.cols, CV_8UC1, Scalar(255));
		 for (int z = 0; z < n; z++)
		 {
			 dst = dilatare(eroziune(src));
			 dst.copyTo(src);
		 }
		 imshow("sursa", src1);
		 imshow("destinatie", dst);
		 waitKey();

	 }
 }

 void lab7inchidere()
 {
	 int n;
	 printf("De cate ori?\n");
	 scanf("%d", &n);
	 char fname[MAX_PATH];
	 while (openFileDlg(fname))
	 {
		 Mat src = imread(fname, IMREAD_GRAYSCALE);
		 Mat src1 = imread(fname, IMREAD_GRAYSCALE);
		 Mat dst(src.rows, src.cols, CV_8UC1, Scalar(255));
		 for (int z = 0; z < n; z++)
		 {
			 dst = eroziune(dilatare(src));
			 dst.copyTo(src);
		 }
		 imshow("sursa", src1);
		 imshow("destinatie", dst);
		 waitKey();

	 }
 }



 void lab7contur()
 {
	 char fname[MAX_PATH];
	 while (openFileDlg(fname))
	 {
		 Mat src = imread(fname, IMREAD_GRAYSCALE);
		 Mat src1 = imread(fname, IMREAD_GRAYSCALE);
		 Mat dst(src.rows, src.cols, CV_8UC1, Scalar(255));
		 src1 = eroziune(src);
		 

		 for (int i = 1; i < src.rows - 1; i++)
		 {
			 for (int j = 1; j < src.cols - 1; j++)
			 {
				 if (src.at<uchar>(i, j) == src1.at<uchar>(i, j))
				 {
					 dst.at<uchar>(i, j) = 255;
				 }
				 else
				 {
					 dst.at<uchar>(i, j) = 0;
				 }

			 }
		 }


		 imshow("sursa", src);
		 imshow("destinatie", dst);
		 waitKey();

	 }
 }

 //FINAL LAB 7
 //INCEPUT LAB 8



 int h[256] = { 0 };


 float medie(Mat src)
 {
	 memset(h, 0, 256 * sizeof(int));
	 int A = src.rows * src.cols;

	 for (int i = 1; i < src.rows - 1; i++)
	 {
		 for (int j = 1; j < src.cols - 1; j++)
		 {
			 uchar pixel = src.at<uchar>(i, j);
			 h[pixel]++;

		 }
	 }

	 float g = 0;
	 for (int i = 0; i < 256; i++) {
		 g += i * h[i];
	 }
	 g = (float)g / A;

	 printf("Medie = %f\n", g);
	 return g;
 }

  void lab8ex1()
  {
	  char fname[MAX_PATH];
	  int hist[256] = {}, histCumul[256] = {};
	  while (openFileDlg(fname))
	  {
		  Mat src = imread(fname, IMREAD_GRAYSCALE);
		  memset(h, 0, 256 * sizeof(int));
		  int A = src.rows * src.cols;
		  float deviatie = 0.0;


		  float md = medie(src);
		  for (int i = 0; i < 256; i++)
		  {
			  deviatie += (float)(i - md) * (i - md) * h[i];
		  }
		  deviatie = (float)deviatie / A;
		  deviatie = sqrt(deviatie);

		  for (int i = 1; i < src.rows - 1; i++) {
			  for (int j = 1; j < src.cols - 1; j++) {
				  hist[src.at<uchar>(i, j)]++;
			  }
		  }
		  
		  for (int i = 0; i <= 255; i++) {
			  for (int j = 0; j <= i; j++) {
				  histCumul[i] += hist[j];
			  }
		  }

		  printf("Deviatie = %f\n", deviatie);
		  imshow("Sursa", src);
		  showHistogram("Histograma", hist, 256, 256);
		  showHistogram("Histograma Cumulativa", histCumul, 256, 256);
		  waitKey();
	  }
  }

  void lab8ex2()
  {
	  char fname[MAX_PATH];
	  while (openFileDlg(fname))
	  {
		  Mat src = imread(fname, IMREAD_GRAYSCALE);
		  int histo[256] = {};
		  int height = src.rows;
		  int width = src.cols;

		  int I_min = 256;
		  int I_max = -1;

		  for (int i = 0; i < height; i++)
		  {
			  for (int j = 0; j < width; j++)
			  {
				  uchar current = src.at<uchar>(i, j);
				  histo[current]++;
			  }
		  }
		  Mat dst(src.rows, src.cols, CV_8UC1, Scalar(255));

		  for (int i = 0; i < 256; i++) {
			  if (histo[i] != 0 && i < I_min) {
				  I_min = i;
			  }
			  if (histo[i] != 0 && i > I_max) {
				  I_max = i;
			  }
		  }

		  float T = (float)(I_min + I_max) / 2;
		  float T_previous = T;

		  do {
			  float medie1 = 0;
			  float medie2 = 0;
			  float n1 = 0;
			  float n2 = 0;

			  for (int i = I_min; i <= T; i++) {

				  medie1 += i * histo[i];
				  n1 += histo[i];
			  }

			  medie1 = medie1 / n1;

			  for (int j = T; j <= I_max; j++) {

				  medie2 += j * histo[j];
				  n2 += histo[j];
			  }

			  medie2 = medie2 / n2;
			  T_previous = T;
			  T = (float)(medie1 + medie2) / 2;

		  } while (abs(T - T_previous) > 0);

		  printf("TRESHHOLD : %f\n",T);
		  for (int i = 0; i < height; i++) {
			  for (int j = 0; j < width; j++) {

				  if (src.at<uchar>(i, j) >= T)
					  dst.at<uchar>(i, j) = 255;	
				  else
					  if (src.at<uchar>(i, j) < T)
						  dst.at<uchar>(i, j) = 0;	
			  }
		  }

		  imshow("Sursa", src);
		  imshow("Destinatie binarizata", dst);
		  waitKey(0);
	  }
  }
  
 
  void histogramEqualization()
  {
	  memset(h, 0, 256 * sizeof(int));
	  char fname[MAX_PATH];
	  while (openFileDlg(fname))
	  {
		  Mat src = imread(fname, IMREAD_GRAYSCALE);
		  Mat dst(src.rows, src.cols, CV_8UC1, Scalar(255));
		  int h2[256] = { 0 };
		  int height = src.rows;
		  int width = src.cols;
		  for (int i = 0; i < height; i++)
		  {
			  for (int j = 0; j < width; j++)
			  {
				  uchar current = src.at<uchar>(i, j);
				  h[current]++;
			  }
		  }
		  int M = height * width;
		  float p[256] = {0};
		  for (int i = 0; i < 256; i++)
		  {
			  p[i] = (float)h[i] / M;
		  }
		  float pc[256] = { 0 };
		  for (int i = 0; i < 256; i++) {
			  for (int k = 0; k <= i; k++)
			  {
				  pc[i] += p[k];
			  }
		  }
		  for (int i = 0; i < height; i++)
		  {
			  for (int j = 0; j < width; j++)
			  {
				  dst.at<uchar>(i, j) = 255 * pc[src.at<uchar>(i, j)];
			  }
		  }
		  for (int i = 0; i < height; i++)
		  {
			  for (int j = 0; j < width; j++)
			  {
				  uchar current = dst.at<uchar>(i, j);
				  h2[current]++;
			  }
		  }
		  imshow("Sursa", src);
		  showHistogram("H sursa", h, 256, 256);
		  showHistogram("H Eq", h2, 256, 256);
		  imshow("EQ", dst);
		  waitKey(0);

	  }

  }

  void gamma_correction(float g0, float g1) {

	  char fname[MAX_PATH];
	  while (openFileDlg(fname))
	  {
		  Mat src = imread(fname, IMREAD_GRAYSCALE);

		  int height = src.rows;
		  int width = src.cols;
		  Mat dst(src.rows, src.cols, CV_8UC1, Scalar(255));//compression
		  Mat dst1(src.rows, src.cols, CV_8UC1, Scalar(255));//decompression



		  for (int i = 0; i < height; i++) {
			  for (int j = 0; j < width; j++) {

				  float val0 = 255 * (pow(((float)src.at<uchar>(i, j) / 255), g0));
				  float val1 = 255 * (pow(((float)src.at<uchar>(i, j) / 255), g1));

				  if (val0 >= 255)
					  dst.at<uchar>(i, j) = 255;
				  else if (val0 <= 0)
					  dst.at<uchar>(i, j) = 0;
				  else dst.at<uchar>(i, j) = val0;

				  if (val1 >= 255)
					  dst1.at<uchar>(i, j) = 255;
				  else if (val1 <= 0)
					  dst1.at<uchar>(i, j) = 0;
				  else dst1.at<uchar>(i, j) = val1;
			  }
		  }

		  imshow("Initial", src);
		  imshow("Compression", dst);
		  imshow("Decompression", dst1);
		  waitKey(0);

	  }
  }

  void brightnessContrast(int g_outMin, int g_outMax, int offset) {
	  memset(h, 0, 256 * sizeof(int));
	  int h2[256] = { 0 };
	  int h1[256] = { 0 };
	  char fname[MAX_PATH];
	  while (openFileDlg(fname))
	  {
		  Mat src = imread(fname, IMREAD_GRAYSCALE);
		  int height = src.rows;
		  int width = src.cols;

		  Mat dst(src.rows, src.cols, CV_8UC1, Scalar(255));//contrast
		  Mat dst1 = src.clone();//brightness
		  int sum = 0;
		  for (int i = 0; i < height; i++)
		  {
			  for (int j = 0; j < width; j++)
			  {
				  sum = dst1.at<uchar>(i, j) + offset;
				  if (sum > 255)
				  {
					  dst1.at<uchar>(i, j) = 255;

				  }
				  else

					  if (sum < 0)
					  {
						  dst1.at<uchar>(i, j) = 0;

					  }
					  else
					  {
						  dst1.at<uchar>(i, j) = sum;
					  }
			  }
		  }
		  int I_maxValue;
		  int I_minValue;

		  int g_inMax = -1;
		  int g_inMin = 256;
		  for (int i = 0; i < height; i++)
		  {
			  for (int j = 0; j < width; j++)
			  {
				  uchar current = src.at<uchar>(i, j);
				  h[current]++;
			  }
		  }

		  for (int i = 0; i < height; i++) {
			  for (int j = 0; j < width; j++) {
				  if (src.at<uchar>(i, j) > g_inMax) {
					  g_inMax = src.at<uchar>(i, j);
				  }
				  if (src.at<uchar>(i, j) < g_inMin) {
					  g_inMin = src.at<uchar>(i, j);
				  }
			  }
		  }

		  float raport = (float)(g_outMax - g_outMin) / (g_inMax - g_inMin);

		  for (int i = 0; i < height; i++) {
			  for (int j = 0; j < width; j++) {

				  int g_in = src.at<uchar>(i, j);
				  int g_out = g_outMin + (g_in - g_inMin) * raport;

				  if (g_out <= 0) {
					  dst.at<uchar>(i, j) = 0;
				  }
				  else if (g_out >= 255) {
					  dst.at<uchar>(i, j) = 255;
				  }
				  else {
					  dst.at<uchar>(i, j) = g_out;
				  }
			  }
		  }
		  for (int i = 0; i < height; i++)
		  {
			  for (int j = 0; j < width; j++)
			  {
				  uchar current1 = dst.at<uchar>(i, j);
				  uchar current2 = dst1.at<uchar>(i, j);
				  h2[current2]++;
				  h1[current1]++;
			  }
		  }

		  imshow("Initial image", src);
		  imshow("After Contrast", dst);
		  imshow("After Brightness", dst1);
		  showHistogram("Initial Histogram", h, 256, 256);
		  showHistogram("After Contrast Histogram", h1, 256, 256);
		  showHistogram("After Brigthness Histogram", h2, 256, 256);
		  waitKey(0);
	  }
  }
  

int main()
{
	int op;
	do
	{
		system("cls");
		destroyAllWindows();
		printf("Menu:\n");
		//printf(" 1 - Open image\n");
		//printf(" 2 - Open BMP images from folder\n");
		//printf(" 3 - Image negative - diblook style\n");
		//printf(" 4 - BGR->HSV\n");
		//printf(" 5 - Resize image\n");
		//printf(" 6 - Canny edge detection\n");
		//printf(" 7 - Edges in a video sequence\n");
		//printf(" 7 - L1 Negative Image\n");
		//printf(" 8 - Snap frame from live video\n");
		//printf(" 9 - Mouse callback demo\n");
		//printf(" 10 - Change grey level aditiv\n");
		//printf(" 11 -Create a 4 colors image\n");
		//printf(" 12 -Function for tests\n");
		//printf(" 13 - Change grey level multi\n");
		//printf(" 14 - Create Matrix\n");
		//CERINTE LAB2
		printf(" Cerinte laborator 2\n\n");
		printf(" 15- lab2 pb 1\n");
		printf(" 16- lab2 pb 2\n");
		printf(" 17- lab2 pb 3\n");
		printf(" 18- lab2 pb 4\n");
		printf(" 19- lab2 pb 5\n");
		//LAB 3
		printf(" Cerinte laborator 3\n\n");
		printf(" 20 -lab3 pb 1,2,3 combined\n");
		printf(" 21 -lab3 pb 4\n");
		printf(" 22 -lab3 pb 5\n");
		printf(" 23 -lab3 pb 6\n\n\n");
		//CERINTE LAB 4
		printf(" Cerinte laborator 4\n\n");
		printf(" 24 -Valori geometrice\n");
		//LAB 5
		printf(" Cerinte laborator 5\n\n");
		printf(" 25 - Lab 5 pb 1 si 2\n");
		printf(" 26 - Lab 5 pb 3\n");
		//LAB 6
		printf(" Cerinte laborator 6\n\n");
		printf(" 27 - Lab 6 pb 1si 2\n");
		printf(" 28 - Lab 6 pb 3\n");
		//LAB 7
		printf(" Cerinte laborator 7\n\n");
		printf(" 29 - Lab 7 dilatare\n");
		printf(" 30 - Lab 7 eroziune\n");
		printf(" 31 - Lab 7 deschidere\n");
		printf(" 32 - Lab 7 inchidere\n");
		printf(" 33 - Lab 7 contur\n\n\n");
		//LAB 8
		printf(" 34 - Lab 8 ex 1\n");
		printf(" 35 - Lab 8 ex 2\n");
		printf(" 36 - Lab 8 ex 4\n");
		printf(" 37 - Lab 8 ex 3 corectie gamma\n");
		printf(" 38 - Lab 8 ex 3 modificare\n");
		printf(" 0 - Exit\n\n");
		printf("Option: ");
		scanf("%d",&op);
		switch (op)
		{
			case 1:
				testOpenImage();
				break;
			case 2:
				testOpenImagesFld();
				break;
			case 3:
				testParcurgereSimplaDiblookStyle(); //diblook style
				break;
			case 4:
				//testColor2Gray();
				testBGR2HSV();
				break;
			case 5:
				testResize();
				break;
			case 6:
				testCanny();
				break;
			case 7:
				// testVideoSequence();
				negative_image();
				break;
			case 8:
				testSnap();
				break;
			case 9:
				testMouseClick();
				break;
		///----------------
				//CERINTE LAB 1
			case 10:
				greyleveladitiv();
				break;
			case 11:
				fourcadrecolor();
				break;
			case 12:
				testfct();
				break;
			case 13:
				greylevelmulti();
				break;
			case 14:
				createmat();
				break;
		////-------------------
				//CERiNTE  LAB 2
			case 15:
				lab2pb1();
				break;
			case 16:
				lab2pb2();
				break;
			case 17:
				lab2pb3();
				break;
			case 18:
				lab2pb4();
				break;
			case 19:
				lab2pb5();
				break;
		////--------------------
				//CERINTE LAB 3
			case 20:
				lab3histograma();
				break;
			case 21:
				lab3pb4();
				break;
			case 22:
				lab3pb5();
				break;
			case 23:
				lab3pb6();
				break;
		///---------------------
				//CERINTE LAB 4
			case 24:
				lab4MouseClick();
				break;
		///-------------------
				//CERINTE LAB 5
			case 25:
				lab5pb1();
				break;
			case 26:
				lab5pb3();
				break;
		///---------------------
				///CERINTE LAB 6
			case 27:
				lab6pb1_2();
				break;
			case 28:
				lab6pb3();
				break;
		//------------------
				///CERINTE LAB 6
			case 29:
				lab7dilatare();
				break;
			case 30:
				lab7eroziune();
				break;
			case 31:
				lab7deschidere();
				break;
			case 32:
				lab7inchidere();
				break;
			case 33:
				lab7contur();
				break;
		//--------------------
			case 34:
				lab8ex1();
				break;
			case 35:
				lab8ex2();
				break;
			case 36:
				histogramEqualization();
				break;
			case 37:
				gamma_correction(0.5, 2);
				break;
			case 38:
				brightnessContrast(20, 230, 65);
				break;
		}
	}
	while (op!=0);
	return 0;
}