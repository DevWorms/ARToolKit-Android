/*
 *  ARWrapperNativeCarsExample.cpp
 *  ARToolKit for Android
 *
 *  Disclaimer: IMPORTANT:  This Daqri software is supplied to you by Daqri
 *  LLC ("Daqri") in consideration of your agreement to the following
 *  terms, and your use, installation, modification or redistribution of
 *  this Daqri software constitutes acceptance of these terms.  If you do
 *  not agree with these terms, please do not use, install, modify or
 *  redistribute this Daqri software.
 *
 *  In consideration of your agreement to abide by the following terms, and
 *  subject to these terms, Daqri grants you a personal, non-exclusive
 *  license, under Daqri's copyrights in this original Daqri software (the
 *  "Daqri Software"), to use, reproduce, modify and redistribute the Daqri
 *  Software, with or without modifications, in source and/or binary forms;
 *  provided that if you redistribute the Daqri Software in its entirety and
 *  without modifications, you must retain this notice and the following
 *  text and disclaimers in all such redistributions of the Daqri Software.
 *  Neither the name, trademarks, service marks or logos of Daqri LLC may
 *  be used to endorse or promote products derived from the Daqri Software
 *  without specific prior written permission from Daqri.  Except as
 *  expressly stated in this notice, no other rights or licenses, express or
 *  implied, are granted by Daqri herein, including but not limited to any
 *  patent rights that may be infringed by your derivative works or by other
 *  works in which the Daqri Software may be incorporated.
 *
 *  The Daqri Software is provided by Daqri on an "AS IS" basis.  DAQRI
 *  MAKES NO WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION
 *  THE IMPLIED WARRANTIES OF NON-INFRINGEMENT, MERCHANTABILITY AND FITNESS
 *  FOR A PARTICULAR PURPOSE, REGARDING THE DAQRI SOFTWARE OR ITS USE AND
 *  OPERATION ALONE OR IN COMBINATION WITH YOUR PRODUCTS.
 *
 *  IN NO EVENT SHALL DAQRI BE LIABLE FOR ANY SPECIAL, INDIRECT, INCIDENTAL
 *  OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 *  SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 *  INTERRUPTION) ARISING IN ANY WAY OUT OF THE USE, REPRODUCTION,
 *  MODIFICATION AND/OR DISTRIBUTION OF THE DAQRI SOFTWARE, HOWEVER CAUSED
 *  AND WHETHER UNDER THEORY OF CONTRACT, TORT (INCLUDING NEGLIGENCE),
 *  STRICT LIABILITY OR OTHERWISE, EVEN IF DAQRI HAS BEEN ADVISED OF THE
 *  POSSIBILITY OF SUCH DAMAGE.
 *
 *  Copyright 2015 Daqri LLC. All Rights Reserved.
 *  Copyright 2011-2015 ARToolworks, Inc. All Rights Reserved.
 *
 *  Author(s): Julian Looser, Philip Lamb
 */

#include <AR/gsub_es.h>
#include <Eden/glm.h>
#include <jni.h>
#include <ARWrapper/ARToolKitWrapperExportedAPI.h>
#include <unistd.h> // chdir()
#include <android/log.h>

// Utility preprocessor directive so only one change needed if Java class name changes
#define JNIFUNCTION_DEMO(sig) Java_com_devworms_pepsicorally_SimpleNativeRenderer_##sig

void crear_obj(const char *file, const char *marker, int nummodel, float scale); //mi funcion

extern "C" {
    JNIEXPORT void JNICALL JNIFUNCTION_DEMO(demoInitialise(JNIEnv * env, jobject object)) ;
    JNIEXPORT void JNICALL JNIFUNCTION_DEMO(demoShutdown(JNIEnv * env, jobject object)) ;
    JNIEXPORT void JNICALL JNIFUNCTION_DEMO(demoSurfaceCreated(JNIEnv * env, jobject object)) ;
    JNIEXPORT void JNICALL JNIFUNCTION_DEMO(demoSurfaceChanged(JNIEnv * env, jobject object, jint w, jint h)) ;
    JNIEXPORT void JNICALL JNIFUNCTION_DEMO(demoDrawFrame(JNIEnv * env, jobject obj)) ;
};

typedef struct ARModel {
    int patternID;
    ARdouble transformationMatrix[16];
    bool visible;
    GLMmodel *obj;
} ARModel;

#define NUM_MODELS 15
static ARModel models[NUM_MODELS] = {0};

static float lightAmbient[4] = {0.1f, 0.1f, 0.1f, 1.0f};
static float lightDiffuse[4] = {1.0f, 1.0f, 1.0f, 1.0f};
static float lightPosition[4] = {0.0f, 0.0f, 1.0f, 0.0f};

JNIEXPORT void JNICALL JNIFUNCTION_DEMO(demoInitialise(JNIEnv * env, jobject object)) {

    crear_obj("Data/models/PAPAS/PAPAS_CODIGO_1946.obj", "single;Data/papas_01.pat;80", 0, 0.03f);
    crear_obj("Data/models/CHEETOS.obj", "single;Data/papas_02.pat;80", 1, 0.03f);
    crear_obj("Data/models/RUFFLES.obj", "single;Data/papas_03.pat;80", 2, 0.03f);
    crear_obj("Data/models/MAFER.obj", "single;Data/papas_04.pat;80", 3, 0.03f);
    crear_obj("Data/models/DORITOS.obj", "single;Data/papas_05.pat;80", 4, 0.03f);

    crear_obj("Data/models/EMPERADOR.obj", "single;Data/pepcilindros_02.pat;80", 5, 0.03f);
    crear_obj("Data/models/CHOKIS.obj", "single;Data/pepcilindros_03.pat;80", 6, 0.03f);
    crear_obj("Data/models/CRACKETS.obj", "single;Data/pepcilindros_04.pat;80", 7, 0.03f);
    crear_obj("Data/models/GALLETA QUAKER.obj", "single;Data/pepcilindros_05.pat;80", 8, 0.03f);
    crear_obj("Data/models/KACANG.obj", "single;Data/tazos_01.pat;80", 9, 0.03f);

    crear_obj("Data/models/BOTE AVENA QUAKER.obj", "single;Data/tazos_05.pat;80", 10, 0.03f);
    crear_obj("Data/models/INSPIREKA.obj", "single;Data/tazos_02.pat;80", 11, 0.03f);
    crear_obj("Data/models/TAZO/TAZO_CODIGO_9228.obj", "single;Data/tazos_03.pat;80", 12, 0.03f);
    crear_obj("Data/models/PEPSILINDRO/PEPSILINDRO CODIGO_7354.obj", "single;Data/tazos_04.pat;80", 13, 0.03f);
    crear_obj("Data/models/SABRITAS RETRO.obj", "single;Data/pepcilindros_01.pat;80", 14, 0.03f);
}

void crear_obj(const char *file, const char *marker, int nummodel, float scale) {

    models[ nummodel ].patternID = arwAddMarker( marker );
    arwSetMarkerOptionBool(models[ nummodel ].patternID, ARW_MARKER_OPTION_SQUARE_USE_CONT_POSE_ESTIMATION, false);
    arwSetMarkerOptionBool(models[ nummodel ].patternID, ARW_MARKER_OPTION_FILTERED, true);

    models[ nummodel ].obj = glmReadOBJ2( file , 0, 0); // context 0, don't read textures yet.

    if (!models[ nummodel ].obj) {
        LOGE("Error loading model from file '%s'.", file );
        exit(-1);
    }

    glmScale(models[ nummodel ].obj, scale );
    //glmRotate(models[0].obj, 3.14159f / 2.0f, 1.0f, 0.0f, 0.0f);
    glmCreateArrays(models[ nummodel ].obj, GLM_SMOOTH | GLM_MATERIAL | GLM_TEXTURE);
    models[ nummodel ].visible = false;

	return;
}

JNIEXPORT void JNICALL JNIFUNCTION_DEMO(demoShutdown(JNIEnv * env, jobject object)) {
}

JNIEXPORT void JNICALL JNIFUNCTION_DEMO(demoSurfaceCreated(JNIEnv * env, jobject object)) {
    glStateCacheFlush(); // Make sure we don't hold outdated OpenGL state.

    for (int i = 0; i < NUM_MODELS; i++) {
        if (models[i].obj) {
            glmDelete(models[i].obj, 0);
            models[i].obj = NULL;
        }
    }
}

JNIEXPORT void JNICALL JNIFUNCTION_DEMO(demoSurfaceChanged(JNIEnv * env, jobject object, jint w, jint h)) {
    // glViewport(0, 0, w, h) has already been set.
}

JNIEXPORT void JNICALL JNIFUNCTION_DEMO(demoDrawFrame(JNIEnv * env, jobject obj)) {

    glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    // Set the projection matrix to that provided by ARToolKit.
    float proj[16];
    arwGetProjectionMatrix(proj);
    glMatrixMode(GL_PROJECTION);
    glLoadMatrixf(proj);
    glMatrixMode(GL_MODELVIEW);

    glStateCacheEnableDepthTest();

    glStateCacheEnableLighting();

    glEnable(GL_LIGHT0);

    for (int i = 0; i < NUM_MODELS; i++) {
        models[i].visible = arwQueryMarkerTransformation(models[i].patternID, models[i].transformationMatrix);

        if (models[i].visible) {
            glLoadMatrixf(models[i].transformationMatrix);

            glLightfv(GL_LIGHT0, GL_AMBIENT, lightAmbient);
            glLightfv(GL_LIGHT0, GL_DIFFUSE, lightDiffuse);
            glLightfv(GL_LIGHT0, GL_POSITION, lightPosition);

            glmDrawArrays(models[i].obj, 0);
        }
    }
}
