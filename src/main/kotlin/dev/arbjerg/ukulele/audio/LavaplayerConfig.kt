package dev.arbjerg.ukulele.audio

import com.sedmelluq.discord.lavaplayer.container.MediaContainerRegistry
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager
//import dev.lavalink.youtube.YoutubeAudioSourceManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LavaplayerConfig {
    @Bean
    fun playerManager(): AudioPlayerManager {
        val apm = DefaultAudioPlayerManager()
        val apiKey = System.getenv("YOUTUBE_API_KEY")
        // Then add the rest, while excluding the legacy `YoutubeAudioSourceManager`
        val youtubeSourceManager = YoutubeAudioSourceManager().apply {
            setApiKey(apiKey)
        }
        apm.registerSourceManager(youtubeSourceManager)
        AudioSourceManagers.registerRemoteSources(apm, MediaContainerRegistry.DEFAULT_REGISTRY, YoutubeAudioSourceManager::class.java)
        return apm
    }
}