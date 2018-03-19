package com.ee.project.main.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.ee.project.R;
import com.ee.project.main.ui.fragment.ContactsFragment;
import com.ee.project.main.ui.fragment.HomeFragment;
import com.ee.project.main.ui.fragment.MessageFragment;
import com.ee.project.main.ui.fragment.WorkFragment;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends FragmentActivity implements BottomNavigationBar.OnTabSelectedListener,View.OnClickListener{

    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 2;
    private TextBadgeItem badge;
    private int nums=10; //首先定义为0 测试为10条数据
    //界面主数组
    private Fragment[] fgtmain;
    private Fragment contacts,home,message,work;
    private DrawerLayout mDrawerLayout;
    private NavigationView navView;
    private LinearLayout mLeftMain;
    private CircleImageView iconimage;
    private ImageView mMainImage;

    //本地照片处理
    private static final int PHOTO_REQUEST_CAREMA = 1;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private static final int PHOTO_REQUEST_CUT = 3;
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initView() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        mLeftMain = findViewById(R.id.mLeftMain);
        //主头像
        mMainImage = findViewById(R.id.mMainImage);
        View drawview = navView.inflateHeaderView(R.layout.nav_header);

        iconimage = drawview.findViewById(R.id.icon_image);


        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .setInActiveColor(R.color.colorGery)//设置未选中的Item的颜色，包括图片和文字
                .setActiveColor(R.color.colorbGery)
                .setBarBackgroundColor(R.color.colorBlue);//设置整个控件的背景色

        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);


        badge=new TextBadgeItem()
                .setBorderWidth(0)
                .setAnimationDuration(300)
                .setBackgroundColorResource(R.color.colorRed)
                .setHideOnSelect(false)
                .setText("0");

        //new BottomNavigationItem(R.drawable.btn_nav_home_press, "主页")
        //new BottomNavigationItem(R.drawable.btn_nav_category_press, "发现"
        //new BottomNavigationItem(R.drawable.btn_nav_cart_press, "购物车")
        //new BottomNavigationItem(R.drawable.btn_nav_msg_press, "消息")
        //new BottomNavigationItem(R.drawable.btn_nav_user_press,"我的")

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.btn_nav_category_press, "工作"))
                .addItem(new BottomNavigationItem(R.drawable.btn_nav_user_press, "联系人"))
                .addItem(new BottomNavigationItem(R.drawable.btn_nav_home_press, "主页"))
                .addItem(new BottomNavigationItem(R.drawable.btn_nav_msg_press, "消息").setBadgeItem(badge))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise();

        setFgtmain();
        setBadgeNum(nums);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                mDrawerLayout.closeDrawers();
                Intent intent;
                intent = new Intent(MainActivity.this,LeftMainActivity.class);
                switch (item.getItemId()){

                    case R.id.nav_main:
                        intent.putExtra("value",1);
                        break;
                    case R.id.nav_friends:
                        intent.putExtra("value",2);
                        break;
                    case R.id.nav_tuig:
                        intent.putExtra("value",3);
                        break;
                    case R.id.nav_cloud:
                        intent.putExtra("value",4);
                        break;
                    case R.id.nav_location:
                        intent.putExtra("value",5);
                        break;
                    case R.id.nav_set:
                        intent.putExtra("value",6);
                        break;
                }
                startActivity(intent);
                return  true;
            }
        });

    }

    private void initEvent(){
        bottomNavigationBar.setTabSelectedListener(this);
        mLeftMain.setOnClickListener(this);
        iconimage.setOnClickListener(this);
    }

    private void setFgtmain() {
        try {
            home = new HomeFragment();
            work = new WorkFragment();
            message = new MessageFragment();
            contacts = new ContactsFragment();
            fgtmain = new Fragment[]{work, contacts, home, message};
            getSupportFragmentManager().beginTransaction().add(R.id.currentFL, work)
                    .add(R.id.currentFL, contacts).add(R.id.currentFL, home)
                    .add(R.id.currentFL, message).show(home).hide(work)
                    .hide(contacts).hide(message).commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private int currentIndex = 2;
    /**
     * 选中切换Fragment的代码
     * @param index
     */
    private void switchFragment(int index){
        try{
            if(currentIndex!=index){
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.hide(fgtmain[currentIndex]);
                if(!fgtmain[index].isAdded()){  //是否已经添加了
                    ft.add(R.id.currentFL,fgtmain[index]);
                }

                ft.show(fgtmain[index]).commit();
            }
            currentIndex = index;
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


    @Override
    public void onTabSelected(int position) {
        switchFragment(position);
    }

    @Override
    public void onTabUnselected(int position) {
        //switchFragment(position);
    }

    @Override
    public void onTabReselected(int position) {
        Log.i("111",position+"");
    }


    /**
     * 设置tab数字提示加缩放动画
     */
    private void setBadgeNum(int num) {
        badge.setText(String.valueOf(num));
        if (num <= 0) {
            nums = 0;
            badge.hide();
            badge.setText("0");
        } else {
            badge.show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mLeftMain:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.icon_image:
                changeHeadIcon();
                break;
        }
    }

    private void changeHeadIcon() {
        final CharSequence[] items = { "相册", "拍照" };
        AlertDialog dlg = new AlertDialog.Builder(MainActivity.this).setTitle("选择图片").setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // 这里item是根据选择的方式，
                if (item == 0) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,
                            PHOTO_REQUEST_GALLERY);
                } else {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        tempFile = new File(Environment.getExternalStorageDirectory(),
                                PHOTO_FILE_NAME);
                        Uri uri = Uri.fromFile(tempFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        startActivityForResult(intent,PHOTO_REQUEST_CAREMA);
                    } else {
                        Toast.makeText(MainActivity.this, "未找到存储卡，无法存储照片！",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }).create();
        dlg.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                Uri uri = data.getData();
                Log.e("图片路径？？", data.getData() + "");
                crop(uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(MainActivity.this, "未找到存储卡，无法存储照片！",
                        Toast.LENGTH_SHORT).show();
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            if (data != null) {
                final Bitmap bitmap = data.getParcelableExtra("data");
                iconimage.setImageBitmap(bitmap);
                mMainImage.setImageBitmap(bitmap);
                // 保存图片到internal storage
                FileOutputStream outputStream;
                try {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    out.flush();
                    // out.close();
                    // final byte[] buffer = out.toByteArray();
                    // outputStream.write(buffer);
                    outputStream = MainActivity.this.openFileOutput("_head_icon.jpg", Context.MODE_PRIVATE);
                    out.writeTo(outputStream);
                    out.close();
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                if (tempFile != null && tempFile.exists())
                    tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void crop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

}
