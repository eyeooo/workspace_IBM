/*
 * Created by SharpDevelop.
 * User: office
 * Date: 2010-5-6
 * Time: 10:24
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */
using System;
using System.Drawing;
using System.IO;
using System.Runtime.InteropServices;
using System.Text;
using System.Windows.Forms;

namespace Sunday_Demo
{
	/// <summary>
	/// Description of MainForm.
	/// </summary>
	public partial class MainForm : Form
	{
		public MainForm()
		{
			//
			// The InitializeComponent() call is required for Windows Forms designer support.
			//
			InitializeComponent();
			
			//
			// TODO: Add constructor code after the InitializeComponent() call.
			//
		}
		
		void MainFormLoad(object sender, System.EventArgs e)
		{
            LibIndex = LoadLibFromFile("演示.lib");
		}

        public int LibIndex = 0;


        [DllImport("演示.dll")]
        public static extern int LoadLibFromFile(string LibFilePath);


        [DllImport("演示.dll")]
        public static extern bool GetCodeFromFile(int LibFileIndex, string FilePath, StringBuilder Code);

        [DllImport("演示.dll")]
        public static extern bool GetCodeFromBuffer(int LibFileIndex, byte[] FileBuffer, int ImgBufLen, StringBuilder Code);

		
		
		void Button3Click(object sender, EventArgs e)
		{
            pictureBox1.Image = Image.FromFile("演示.gif");

			button3.Enabled = false;
			StringBuilder Result = new StringBuilder('\0',256);



            //以下使用GetCodeFromBuffer接口
            FileStream fsMyfile = File.OpenRead("演示.gif"); 
			int FileLen = (int)fsMyfile.Length;
			byte[] Buffer = new byte[FileLen]; 
			fsMyfile.Read (Buffer, 0, FileLen); 
			fsMyfile.Close();

            if (GetCodeFromBuffer(LibIndex,Buffer, FileLen, Result))
				textBox3.Text = Result.ToString();
			else
				textBox3.Text = "识别失败";
			
			button3.Enabled = true;
		}

      

        

       
	}
}