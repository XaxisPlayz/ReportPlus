package me.xaxis.reportplus.reports;

import me.xaxis.reportplus.Main;
import me.xaxis.reportplus.enums.ReportState;
import me.xaxis.reportplus.enums.ReportType;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.UUID;

public class Report{

    private final ConfigurationSection section;

    public Report(@NotNull Main plugin, @NotNull UUID playerUUID, @NotNull UUID reporter, @NotNull ReportType reportType) throws IOException {

        section = plugin.getReportYML().getFile().createSection(playerUUID.toString());

        section.set("player_UUID", playerUUID.toString());
        section.set("timestamp", System.currentTimeMillis());
        section.set("reporter_UUID", reporter.toString());
        section.set("report_type", reportType.toString());
        section.set("report_state", ReportState.OPEN.name());

        plugin.getReportYML().save();

        ReportManager.addReport(this, playerUUID);

    }

    public UUID getPlayerUUID(){
        return UUID.fromString( section.getString("player_UUID") );
    }
    public String getPlayerName(){
        return Bukkit.getPlayer(UUID.fromString( section.getString("player_UUID") )).getName();
    }
    public ReportState getState() {
        return ReportState.valueOf(section.getString("report_state"));
    }
    public void setState(ReportState state){
        section.set("report_state", state.name());
    }
    public void resolve(){
        setState(ReportState.RESOLVED);
    }
    public String getTargetName(){
        return Bukkit.getPlayer(UUID.fromString( section.getString("reporter_UUID") )).getName();
    }
    public Long getTimestamp(){
        return section.getLong("timestamp");
    }
    public UUID getReporterUUID(){
        return UUID.fromString( section.getString("reporter_UUID") );
    }
    public ReportType getReportType(){
        return ReportType.valueOf( section.getString("report_type") );
    }
    public Report(@NotNull Main plugin, @NotNull UUID uuid){

        section = plugin.getReportYML().getFile().getConfigurationSection(uuid.toString());

        ReportManager.addReport(this, uuid);

    }



}
