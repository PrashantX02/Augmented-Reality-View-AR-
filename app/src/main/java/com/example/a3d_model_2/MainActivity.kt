package com.example.a3d_model_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.core.view.isGone
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.node.ModelNode

class MainActivity : AppCompatActivity() {

    private lateinit var sceneView: ArSceneView
    private lateinit var place : Button
    private lateinit var modelNode: ArModelNode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        sceneView = findViewById(R.id.sceneView)

        place = findViewById(R.id.button)

        modelNode = ArModelNode().apply {
            loadModelGlbAsync(
                glbFileLocation = "model/kiki.glb"
            )
            {
                sceneView.planeRenderer.isVisible = true
            }
            onAnchorChanged = {
                place.visibility = if (anchor != null) View.GONE else View.VISIBLE
            }
        }

        sceneView.addChild(modelNode)

        place.setOnClickListener {
            modelNode?.anchor()

            sceneView.planeRenderer.isVisible = true
        }
    }
}