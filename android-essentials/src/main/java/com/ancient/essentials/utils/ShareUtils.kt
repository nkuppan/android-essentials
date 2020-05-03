package com.ancient.essentials.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.ancient.essentials.R

/**
 * Created by ancientinc on 20/04/20.
 **/
object ShareUtils {

    private const val MARKET_DETAILS_ID = "market://details?id=%s"

    /**
     * @param context to create a rate us dialog
     */
    fun openPlayStorePage(context: Context) {
        val uri = Uri.parse(String.format(MARKET_DETAILS_ID, context.packageName))
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        try {
            context.startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            AlertDialogUtils.createConformationDialog(
                context, 
                context.getString(R.string.error_title),
                context.getString(R.string.play_store_not_available),
                false
            )
        }
    }

    /**
     * @param aContext      create a dialog
     * @param shareContent content to share
     */
    private fun createShareIntent(aContext: Context, shareContent: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareContent)
        sendIntent.type = "text/plain"
        aContext.startActivity(
            Intent.createChooser(
                sendIntent,
                aContext.resources.getText(R.string.share_content)
            )
        )
    }

    /**
     * @param aContext       to open a activity
     * @param aWebPageURL action URL to open
     */
    fun openWebPage(aContext: Context, aWebPageURL: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(aWebPageURL)
        aContext.startActivity(
            Intent.createChooser(
                intent,
                aContext.resources.getText(R.string.open_web_page)
            )
        )
    }

    /**
     * Used to create a email action intent and opening the chooser to
     *
     * @param aContext to open email application
     * @param aEmailId email id of the recipient to send the content
     * @param aEmailSubject subject to be attached into the email
     * @param aEmailContent content of the of email
     */
    fun sendEmailFeedback(
        aContext: Context,
        aEmailId: String,
        aEmailSubject: String,
        aEmailContent: String
    ) {

        val emailIntent = Intent(Intent.ACTION_SEND)

        emailIntent.type = "plain/text"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(aEmailId))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, aEmailSubject)
        emailIntent.putExtra(Intent.EXTRA_TEXT, aEmailContent)

        aContext.startActivity(
            Intent.createChooser(
                emailIntent,
                aContext.getString(R.string.send_email_title)
            )
        )
    }

    /**
     * Method is used to share our application to others
     *
     * @param aContext to share app
     */
    fun shareContent(aContext: Context, aShareContent: String) {
        createShareIntent(aContext, aShareContent)
    }
}