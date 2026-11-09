# WonderPlay Academy Proguard Rules

# Keep Compose-specific rules (usually handled by Compose libraries, but good to ensure)
-keepclassmembers class androidx.compose.runtime.Recomposer { *; }
-keep class androidx.compose.ui.platform.** { *; }

# Keep data models used for serialization (if any)
-keep class com.wonderplay.academy.data.model.** { *; }

# Keep TTS and Audio related classes
-keep class com.wonderplay.academy.audio.** { *; }

# Standard Android keep rules
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class * extends androidx.core.app.ComponentActivity

# Prevent R8 from removing UI components referenced by Compose
-keep @androidx.compose.runtime.Composable class *
-keepclassmembers class * {
    @androidx.compose.runtime.Composable *;
}
