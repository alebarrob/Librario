package barrera.alejandro.librario.core.data.di

import android.content.Context
import androidx.room.Room
import barrera.alejandro.librario.core.data.database.LibrarioRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): LibrarioRoomDatabase {
        return Room.databaseBuilder(
            context,
            LibrarioRoomDatabase::class.java,
            "librario_database"
        ).createFromAsset("database/librario_database.db")
            .build()
    }
}