package com.lqdbg.wallpaper.util.state;

import androidx.appcompat.app.AppCompatActivity;

public class SbActivity extends AppCompatActivity {
    private SbTools sbTools;


    @Override
    protected void onRestart() {
        super.onRestart();
        if (sbTools != null)
            sbTools.init();
    }



    public void setSbStatusBarTools(SbTools pkStatusBarTools) {
        if (pkStatusBarTools != null)
            this.sbTools = pkStatusBarTools;
    }
}
